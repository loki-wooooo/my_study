package io.github.lokiwooooo.aop;

import io.github.lokiwooooo.domain.log.dto.LogDto;
import io.github.lokiwooooo.domain.log.entity.Log;
import io.github.lokiwooooo.domain.log.entity.LogDetailType;
import io.github.lokiwooooo.domain.log.entity.LogType;
import io.github.lokiwooooo.domain.log.repository.LogMapper;
import io.github.lokiwooooo.domain.log.repository.LogRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Enumeration;


@Slf4j
@Aspect
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Component
public class LogAspectConfig {

    private static final String UNKNOWN = "unknown";

    LogMapper logMapper;
    LogRepository logRepository;


    // POST 요청 로깅
    @Around("@annotation(io.github.lokiwooooo.annotaion.PostAnnotation)")
    public Object logPostOperation(ProceedingJoinPoint pjp) throws Throwable {
        return logOperation(pjp, LogDetailType.POST);
    }

    // GET 요청 로깅
    @Around("@annotation(io.github.lokiwooooo.annotaion.GetAnnotation)")
    public Object logGetOperation(ProceedingJoinPoint pjp) throws Throwable {
        return logOperation(pjp, LogDetailType.GET);
    }

    // PUT 요청 로깅
    @Around("@annotation(io.github.lokiwooooo.annotaion.PutAnnotation)")
    public Object logPutOperation(ProceedingJoinPoint pjp) throws Throwable {
        return logOperation(pjp, LogDetailType.PUT);
    }

    // DELETE 요청 로깅
    @Around("@annotation(io.github.lokiwooooo.annotaion.DeleteAnnotation)")
    public Object logDeleteOperation(ProceedingJoinPoint pjp) throws Throwable {
        return logOperation(pjp, LogDetailType.DELETE);
    }

    // 공통 로깅 로직
    private Object logOperation(final ProceedingJoinPoint pjp, final LogDetailType operationType) throws Throwable {
        LocalDateTime requestTime = LocalDateTime.now();
        long startTime = System.currentTimeMillis();

        try {
            Object result = pjp.proceed();

            long endTime = System.currentTimeMillis();
            long executionTime = endTime - startTime;
            LocalDateTime responseTime = LocalDateTime.now();

            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

            String logContent = String.format(
                    "작업 유형: %s%n요청 URL: %s%n요청 시간: %s%n응답 시간: %s%n실행 시간: %dms",
                    operationType,
                    getRequestURI(request),
                    requestTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                    responseTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                    executionTime
            );

            log.info("API 실행 정보:\n{}", logContent);
            insertLogs(request, operationType, logContent);

            return result;

        } catch (Exception e) {
            long endTime = System.currentTimeMillis();
            long executionTime = endTime - startTime;
            LocalDateTime errorTime = LocalDateTime.now();

            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

            String errorLogContent = String.format(
                    "작업 유형: %s%n요청 URL: %s%n요청 시간: %s%n에러 발생 시간: %s%n실행 시간: %dms%n에러 메시지: %s",
                    operationType,
                    getRequestURI(request),
                    requestTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                    errorTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                    executionTime,
                    e.getMessage()
            );

            log.error("API 에러:\n{}", errorLogContent);
            insertLogs(request, operationType, errorLogContent);

            throw e;
        }
    }

    private void insertLogs(final HttpServletRequest request,
                            final LogDetailType logDetailType,
                            final String logContent) {
        String userName = "SYSTEM_USER";
        String userId = "SYSTEM_USER_ID";
        String requestIp = getClientIp(request);

        // JWT 토큰에서 사용자 정보 추출
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
        }

        // logging
        LogDto logDto = LogDto.builder()
                .type(LogType.API)
                .detailType(logDetailType)
                .content(logContent)
                .ip(requestIp)
                .createdOn(LocalDateTime.now())
                .lastEditedOn(LocalDateTime.now())
                .build();

        Log log = logMapper.toEntity(logDto);
        logRepository.save(log);
    }

    /**
     * 요청 URI 정보 리턴
     */
    private String getRequestURI(final HttpServletRequest request) {
        return request.getRequestURI().replaceAll(request.getContextPath() + "/rest/market/v1", "");
    }

    /**
     * Client IP 관련 확인
     */
    public String getClientIp(final HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");

        if (ip == null || ip.isEmpty() || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            if (ip.equals("0:0:0:0:0:0:0:1") || ip.equals("::1") || ip.equals("127.0.0.1")) {
                try {
                    // 모든 네트워크 인터페이스 조회
                    Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
                    while (interfaces.hasMoreElements()) {
                        NetworkInterface ni = interfaces.nextElement();
                        // 활성화되어 있고 루프백이 아닌 인터페이스만 확인
                        if (!ni.isLoopback() && ni.isUp()) {
                            Enumeration<InetAddress> addresses = ni.getInetAddresses();
                            while (addresses.hasMoreElements()) {
                                InetAddress addr = addresses.nextElement();
                                // IPv4 주소만 처리
                                if (addr instanceof Inet4Address) {
                                    ip = addr.getHostAddress();
                                    return ip;
                                }
                            }
                        }
                    }
                } catch (SocketException e) {
                    log.warn("네트워크 인터페이스 조회 중 오류 발생", e);
                }
            }
        }

        return ip.trim();
    }

}