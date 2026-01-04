package io.github.lokiwooooo.demo.section7.class03;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

// Schedulers.newParallel()을 적용
@Slf4j
public class SchedulersNewParallelExample01 {
    public static void main(String[] args) throws InterruptedException{
        Mono<Integer> flux = Mono.just(1)
                .publishOn(Schedulers.newParallel("Parallel Thread", 4, true));

        log.info("# Start");

        flux.subscribe(data -> {
            try{
                Thread.sleep(5000L);
            }catch (Exception e){
                e.printStackTrace();
            }
            log.info("subscribe1: {}", data);
        });

        flux.subscribe(data -> {
            try{
                Thread.sleep(4000L);
            }catch (Exception e){
                e.printStackTrace();
            }
            log.info("subscribe2: {}", data);
        });

        flux.subscribe(data -> {
            try{
                Thread.sleep(3000L);
            }catch (Exception e){
                e.printStackTrace();
            }
            log.info("subscribe3: {}", data);
        });

        flux.subscribe(data -> {
            try{
                Thread.sleep(2000L);
            }catch (Exception e){
                e.printStackTrace();
            }
            log.info("subscribe4: {}", data);
        });

        Thread.sleep(6000L);
    }
}
