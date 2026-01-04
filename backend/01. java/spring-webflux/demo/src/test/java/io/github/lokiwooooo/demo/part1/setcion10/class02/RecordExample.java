package io.github.lokiwooooo.demo.part1.setcion10.class02;

import reactor.core.publisher.Flux;

public class RecordExample {
    public static Flux<String> getCountry(final Flux<String> source) {
        return source
                .map(country -> country.substring(0, 1).toUpperCase() + country.substring(1));
    }
}
