package io.github.lokiwooooo.demo.part2.section11.class03;

import io.github.lokiwooooo.demo.part2.section11.class01.SampleData;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.util.function.Tuple2;

import java.util.HashMap;
import java.util.Map;

public class CoronaVaccineService {
    private static Map<SampleData.CoronaVaccine, Tuple2<SampleData.CoronaVaccine, Integer>> vaccineMap = getCoronaVaccinesMap();

    public static Mono<Boolean> isGreaterThan(SampleData.CoronaVaccine coronaVaccine, int amount) {
        return Mono
                .just(vaccineMap.get(coronaVaccine).getT2() > amount)
                .publishOn(Schedulers.parallel()); // 별도 쓰레드로 진행
    }

    public static Map<SampleData.CoronaVaccine, Tuple2<SampleData.CoronaVaccine, Integer>> getCoronaVaccinesMap() {
        return SampleData.getCoronaVaccinesMap();
    }
}
