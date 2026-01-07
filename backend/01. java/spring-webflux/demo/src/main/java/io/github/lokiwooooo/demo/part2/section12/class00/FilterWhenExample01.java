package io.github.lokiwooooo.demo.part2.section12.class00;

import io.github.lokiwooooo.demo.part2.section11.class01.SampleData;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import static io.github.lokiwooooo.demo.part2.section12.class00.CoronaVaccineService.isGreaterThan;

/**
 * 백신의 재고를 기준 이상으로 보유하고 있는 백신만 출력하도록 하는 예제
 * - filterWhen을 사용해서 비동기적으로 필터링 한다.
 * */
@Slf4j
public class FilterWhenExample01 {
    public static void main(String[] args) throws Exception{
        Flux
                .fromIterable(SampleData.coronaVaccineNames)
                // filterWhen -> 비동기로 처리
                .filterWhen(vaccine -> isGreaterThan(vaccine, 3_000_000))
                .subscribe(data -> log.info("data: {}", data))
                ;

        Thread.sleep(1000L);
    }
}
