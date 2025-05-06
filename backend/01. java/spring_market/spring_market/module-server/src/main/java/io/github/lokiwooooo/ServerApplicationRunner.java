package io.github.lokiwooooo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ServerApplicationRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("Spring-Market Application Runner");
        log.info("parameter arguments: {}", args.getNonOptionArgs());
        log.info("parameter option arguments: {}", args.getOptionNames());

        // 옵션 인자 처리 예시
//        args.getOptionNames().forEach(optionName -> {
//            log.info("옵션: {} = {}", optionName, args.getOptionValues(optionName));
//        });

        // 여기에 애플리케이션 시작 시 필요한 초기화 로직을 구현합니다.
        initializeApplication();
    }

    private void initializeApplication() {
//        log.info("애플리케이션 초기화 진행 중...");
        // 초기화 로직 구현
        // 예: 캐시 워밍업, 초기 데이터 로드 등
    }
}
