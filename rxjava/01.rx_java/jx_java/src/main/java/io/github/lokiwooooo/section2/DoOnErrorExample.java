package io.github.lokiwooooo.section2;

import io.github.lokiwooooo.utils.LogType;
import io.github.lokiwooooo.utils.Logger;
import io.reactivex.Observable;

// onError 이벤트 발생 전에 호출되는 doOnError의 사용 예제
public class DoOnErrorExample {
    public static void main(String[] args) {
        Observable.just(3, 6, 9, 12, 15, 20)
                //결합연산자 함수 - 생산자쪽 통지 원본데이터와 zipwith Observable을 통지된 데이터중 동일한 index를 결합함)
                .zipWith(Observable.just(1, 2, 3, 4, 0, 5), (a, b) -> a / b) // a 원본데이터, b. zipwith Observable 데이
                .doOnError(error -> Logger.log(LogType.DO_ON_ERROR, "# 생산자: 에러발생 - " + error.getMessage()))
                .subscribe(
                        data -> Logger.log(LogType.ON_NEXT, data), // 소비자쪽에 전달함
                        error -> Logger.log(LogType.ON_ERROR, error) // 두번쨰 에러 람다 표현식에 표현이 됨
                );
    }
}
