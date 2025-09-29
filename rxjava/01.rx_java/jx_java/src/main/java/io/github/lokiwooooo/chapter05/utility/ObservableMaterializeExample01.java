package io.github.lokiwooooo.chapter05.utility;

import io.github.lokiwooooo.utils.LogType;
import io.github.lokiwooooo.utils.Logger;
import io.reactivex.Observable;


/*
* materialize 메타정보
* value: 실제 데이터 값 (onNext 이벤트로 발행된 원본 데이터)
* isOnNext: 현재 Notification이 onNext 이벤트인지 여부
* isOnComplete: 현재 Notification이 onComplete 이벤트인지 여부
* isOnError: 현재 Notification이 onError 이벤트인지 여부
* 에러 객체 (onError 이벤트일 경우 포함)
* */
public class ObservableMaterializeExample01 {
    public static void main(String[] args) {
        Observable.just(1, 2, 3, 4, 5, 6)
                .materialize() // 데이터 통지타입까지 소비자에게 통지함
                .subscribe(notification -> {
                    String notificationType = notification.isOnNext() ? "onNext()" : (notification.isOnError() ? "onError()" : "onComplete()");
                    Logger.log(LogType.PRINT, "notification 타입: " + notificationType);
                    Logger.log(LogType.ON_NEXT, notification.getValue());// 생산자에서 통지한 데이터를 출력함
                });
    }
}
