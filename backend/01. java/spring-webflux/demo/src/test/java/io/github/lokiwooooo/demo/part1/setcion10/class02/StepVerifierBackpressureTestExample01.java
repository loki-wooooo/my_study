package io.github.lokiwooooo.demo.part1.setcion10.class02;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

/*
* Backpressure 전략에 따라 Exception이 발생하는 예제
* - request 데이터 개수보다 많은 데이터가 Emit 되어 OverFlowException이 발생
* - OverFlowException이 발생하게 된 데이터는 discard 된다.
* - 나머지 emit 된 데이터들은 Hooks.onNextDropped()에 의해 Drop 된다.
*
* - Discard: 에러를 일으킨 원인이 된 데이터를 안전하게 자원 해제하는 것.
* - Drop: 에러 때문에 스트림이 닫힌 줄도 모르고 뒤늦게 도착한 나머지 데이터들을 폐기하는 것.
* Backpressure는 데이터를 받는 쪽(Downstream)이 감당할 수 없을 만큼 데이터가 쏟아질 때, 시스템이 망가지지 않도록 조절하는 안전장치
* */
public class StepVerifierBackpressureTestExample01 {

    @Test
    public void generateNumberTest() {
        StepVerifier
                .create(BackpressureExample.generateNumberByErrorStrategy(), 1L)
                .thenConsumeWhile(num -> num >= 1) //emit한 데이터를 소비함
                .verifyComplete();
    }
}
