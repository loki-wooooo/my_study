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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


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
        String userName = request.getRemoteUser();
        String requestIp = getClientIp(request);

        if (userName == null) {
            userName = "SYSTEM_USER";
        }

        LogDto logDto = LogDto.builder()
                .type(LogType.API)
                .detailType(logDetailType)
                .content(logContent)
                .ip(requestIp)
                .isUse(true)
                .createdOn(LocalDateTime.now())
                .lastEditedOn(LocalDateTime.now())
                .createdUserName(userName)
                .lastEditedUserName(userName)
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
    public String getClientIp(final HttpServletRequest httpServletRequest) {
        String ip = httpServletRequest.getHeader("x-original-forwarded-for");

        if (ip == null || ip.isEmpty() || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = httpServletRequest.getHeader("Proxy-Client-IP");
        }

        if (ip == null || ip.isEmpty() || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = httpServletRequest.getHeader("WL-Proxy-Client-IP");
        }

        if (ip == null || ip.isEmpty() || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = httpServletRequest.getHeader("HTTP_CLIENT_IP");
        }

        if (ip == null || ip.isEmpty() || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = httpServletRequest.getHeader("HTTP_X_FORWARDED_FOR");
        }

        if (ip == null || ip.isEmpty() || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = httpServletRequest.getRemoteAddr().toLowerCase();
        }

        return ip;
    }

}