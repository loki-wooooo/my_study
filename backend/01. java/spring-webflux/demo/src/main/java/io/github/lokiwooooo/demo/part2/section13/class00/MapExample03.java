package io.github.lokiwooooo.demo.part2.section13.class00;

import io.github.lokiwooooo.demo.part2.section11.class01.SampleData;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

/**
 * map 활용 예제
 * - Morse Code를 알파벳으로 변환하는 예제
 * */
@Slf4j
public class MapExample03 {

    public static void main(String[] args) {
        Flux
                .just("...", "---", "...")
                .map(code -> transformMorseCode(code))
                .subscribe(data -> log.info("data: {}", data));
    }

    public static String transformMorseCode(final String morseCode){
        return SampleData.morseCodeMap.get(morseCode);
    }

}
