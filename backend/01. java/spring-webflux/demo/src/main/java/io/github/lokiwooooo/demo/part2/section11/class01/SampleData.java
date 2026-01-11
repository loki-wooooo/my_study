package io.github.lokiwooooo.demo.part2.section11.class01;

import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
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

    public static Map<String, String> morseCodeMap = new HashMap<>();
    public static Map<String, Mono<String>> nppMap = new HashMap<>();
    public static String[] morseCodes = {
            ".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--",
            "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."};

    static {
        for (char c = 'a'; c <= 'a' + 25; c++) {
            morseCodeMap.put(morseCodes[c - ('z' - 25)], Character.toString(c));
        }

        nppMap.put("Ontario", Mono.just("Ontario Done").delayElement(Duration.ofMillis(1500L)));
        nppMap.put("Vermont", Mono.just("Vermont Done").delayElement(Duration.ofMillis(400L)));
        nppMap.put("New Hampshire", Mono.just("New Hampshire Done").delayElement(Duration.ofMillis(700L)));
        nppMap.put("New Jersey", Mono.just("New Jersey Done").delayElement(Duration.ofMillis(500L)));
        nppMap.put("Ohio", Mono.just("Ohio Done").delayElement(Duration.ofMillis(1000L)));
        nppMap.put("Michigan", Mono.just("Michigan Done").delayElement(Duration.ofMillis(200L)));
        nppMap.put("Illinois", Mono.just("Illinois Done").delayElement(Duration.ofMillis(300L)));
        nppMap.put("Virginia", Mono.just("Virginia Done").delayElement(Duration.ofMillis(600L)));
        nppMap.put("North Carolina", Mono.just("North Carolina Done").delayElement(Duration.ofMillis(800L)));
        nppMap.put("Georgia", Mono.just("Georgia Done").delayElement(Duration.ofMillis(900L)));
    }

}
