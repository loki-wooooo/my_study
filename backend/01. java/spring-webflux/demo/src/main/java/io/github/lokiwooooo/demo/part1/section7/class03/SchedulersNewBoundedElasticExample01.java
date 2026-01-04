package io.github.lokiwooooo.demo.part1.section7.class03;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

/**
 * Schedulers.newBoundedElastic()을 적용
 */
@Slf4j
public class SchedulersNewBoundedElasticExample01 {
    public static void main(String[] args) throws InterruptedException {
        // 신규 쓰레드 executor를 생성함
        // newBoundedElastic -> daemon thread X- user thread O
        // 즉시 종료를 원하면 scheduler.dispose() 메서드를 호출
        /**
         * User thread (non-daemon): “일반” 스레드. 이 스레드가 하나라도 살아 있으면 JVM이 종료되지 않음.
         * Daemon thread: 백그라운드 작업용 스레드. 남아있는 스레드가 daemon뿐이면 JVM이 바로 종료될 수 있음.
         * */
        Scheduler scheduler = Schedulers.newBoundedElastic(2, 2, "I/O-Thread");
        Mono<Integer> mono = Mono.just(1).subscribeOn(scheduler);

        log.info("# Start");

        mono.subscribe(data -> {
            log.info("subscribe 1 doing {}", data);
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            log.info("subscribe 1 done {}", data);
        });

        mono.subscribe(data -> {
            log.info("subscribe 2 doing {}", data);
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            log.info("subscribe 2 done {}", data);
        });

        mono.subscribe(data -> {
            log.info("subscribe 3 doing {}", data);
        });

        mono.subscribe(data -> {
            log.info("subscribe 4 doing {}", data);
        });

        mono.subscribe(data -> {
            log.info("subscribe 5 doing {}", data);
        });

        mono.subscribe(data -> {
            log.info("subscribe 6 doing {}", data);
        });

    }

}
