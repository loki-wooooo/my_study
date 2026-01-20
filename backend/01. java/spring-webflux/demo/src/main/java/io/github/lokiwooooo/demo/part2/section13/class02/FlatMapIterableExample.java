package io.github.lokiwooooo.demo.part2.section13.class02;

import io.github.lokiwooooo.demo.part2.section11.class01.SampleData;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;

import java.util.List;

/**
 * flatMapIterable 기본 개념 예제
 * - Iterable로 emit된 데이터를 순차적으로 평탄화 한다.
 * - 유형별 코로나 백신 List를 평탄화 하는 예제
 *
 */
@Slf4j
public class FlatMapIterableExample {
    public static void main(String[] args) {
        Flux.just(getViralVectorVaccines(), getRNAVaccines(), getSubUnitVaccines())
                .flatMapIterable(vaccines -> vaccines)
                .subscribe(data -> log.info("data: {}", data));
    }

    private static List<Tuple2<SampleData.CoronaVaccine, Integer>> getViralVectorVaccines() {
        return SampleData.viralVectorVaccines;
    }

    private static List<Tuple2<SampleData.CoronaVaccine, Integer>> getRNAVaccines() {
        return SampleData.mRNAVaccines;
    }

    private static List<Tuple2<SampleData.CoronaVaccine, Integer>> getSubUnitVaccines() {
        return SampleData.subunitVaccines;
    }
}
