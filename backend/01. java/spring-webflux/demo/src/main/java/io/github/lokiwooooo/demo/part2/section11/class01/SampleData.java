package io.github.lokiwooooo.demo.part2.section11.class01;

import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SampleData {
    public static final List<String> coinNames = Arrays.asList("BTC", "ETH", "XRP", "ICX", "EOS", "BCH");
    public static final List<Integer> btcPrices = Arrays.asList(50_000_000, 50_100_100, 50_700_000, 51_500_000, 52_000_000);

    public static final List<Tuple2<String, Integer>> coins =
            Arrays.asList(
                    Tuples.of("BTC", 52_000_000)
                    , Tuples.of("ETH", 1_720_000)
                    , Tuples.of("XRP", 533)
                    , Tuples.of("ICX", 2_080)
                    , Tuples.of("EOS", 4_020)
                    , Tuples.of("BCH", 558_000)
            );

    public static final List<Tuple2<Integer, Long>> btcTopPricesPerYear =
            Arrays.asList(
                    Tuples.of(2010, 565L)
                    , Tuples.of(2011, 36_094L)
                    , Tuples.of(2012, 17_425L)
                    , Tuples.of(2013, 1_405_209L)
                    , Tuples.of(2014, 1_237_182L)
                    , Tuples.of(2015, 557_603L)
                    , Tuples.of(2016, 1_111_811L)
                    , Tuples.of(2017, 22_483_583L)
                    , Tuples.of(2018, 19_521_543L)
                    , Tuples.of(2019, 15_761_568L)
                    , Tuples.of(2020, 22_439_002L)
                    , Tuples.of(2021, 63_364_000L)
            );


    public static final List<CoronaVaccine> coronaVaccineNames = CoronaVaccine.toList();

    public static final List<Tuple2<CoronaVaccine, Integer>> coronaVaccines =
            Arrays.asList(
                    Tuples.of(CoronaVaccine.Pfizer, 1_000_000),
                    Tuples.of(CoronaVaccine.AstraZeneca, 3_000_000),
                    Tuples.of(CoronaVaccine.Moderna, 4_000_000),
                    Tuples.of(CoronaVaccine.Janssen, 2_000_000),
                    Tuples.of(CoronaVaccine.Novavax, 2_500_000)
            );

    public static Map<CoronaVaccine, Tuple2<CoronaVaccine, Integer>> getCoronaVaccinesMap() {
        return coronaVaccines.stream().collect(Collectors.toMap(t1 -> t1.getT1(), t2 -> t2));
    }

    public enum CoronaVaccine {
        Pfizer,
        AstraZeneca,
        Moderna,
        Janssen,
        Novavax;

        public static List<CoronaVaccine> toList() {
            return Arrays.asList(
                    CoronaVaccine.Pfizer, CoronaVaccine.AstraZeneca, CoronaVaccine.Moderna, CoronaVaccine.Janssen, CoronaVaccine.Novavax
            );
        }
    }

    public static Map<Integer, Tuple2<Integer, Long>> getBtcTopPricesPerYearMap() {
        return btcTopPricesPerYear.stream().collect(Collectors.toMap(t1 -> t1.getT1(), t2 -> t2));
    }
}
