package io.github.lokiwooooo.section2;

import io.github.lokiwooooo.utils.LogType;
import io.github.lokiwooooo.utils.Logger;
import io.reactivex.Observable;

// onComplete 이벤트 발생 전에 호출되는 doOnComplete 사용 예제
public class DoOnCompleteExample {

    public static void main(String[] args) {
        Observable.range(1, 5)
                //ON_COMPLETE 완료 직전에 실행
                .doOnComplete(() -> Logger.log(LogType.DO_ON_COMPLETE, "# 생산자: 데이터 통지 완료"))
                .subscribe(
                        data -> Logger.log(LogType.ON_NEXT, data),
                        error -> Logger.log(LogType.ON_ERROR, error),
                        () -> Logger.log(LogType.ON_COMPLETE)
                );
    }
}
