package io.github.lokiwooooo.demo.part2.section13.class02;

import io.github.lokiwooooo.demo.part2.section11.class01.SampleData;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

/**
 * flatMapMany기본 개념 예제
 * - Mono 에서 emit된 데이터를 Flux로 변환
 * - 특정 가격의 BTC를 구매했을 때, 연도별 최고가일 경 수익 금액 계산하기
 * 수익금액 = (현재 가격 + 투자 금액 / 구매시 가격) - 원금
 *
 */
@Slf4j
public class FlatMapManyExample {
    private static final int BUY_PRICE = 500;
    private static final int INVESTMENT_AMOUNT = 1000;

    public static void main(String[] args) {
        Mono
                .just(Tuples.of(BUY_PRICE, INVESTMENT_AMOUNT))
                .flatMapMany(FlatMapManyExample::calculateMaxProfitPerYear)
                .subscribe(profit -> log.info("profit: {}", profit));
    }

    private static Flux<Long> calculateMaxProfitPerYear(final Tuple2<Integer, Integer> buyInfo) {
        return Flux
                .fromIterable(SampleData.btcTopPricesPerYear)
                .map(btcInfo -> btcInfo.getT2() * buyInfo.getT2() / buyInfo.getT1() - buyInfo.getT1());
    }

}
