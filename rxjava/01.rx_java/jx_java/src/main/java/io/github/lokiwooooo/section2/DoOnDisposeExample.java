package io.github.lokiwooooo.section2;

import io.github.lokiwooooo.common.CarMaker;
import io.github.lokiwooooo.common.SampleData;
import io.github.lokiwooooo.utils.LogType;
import io.github.lokiwooooo.utils.Logger;
import io.github.lokiwooooo.utils.TimeUtil;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

import java.util.concurrent.TimeUnit;

// 구독 해지 시 doOnDispose를 이용하여 지정한 처리를 하는 예제
public class DoOnDisposeExample {
    public static void main(String[] args) {
        Observable.fromArray(SampleData.carMakers)
                .zipWith(Observable.interval(300L, TimeUnit.MICROSECONDS), ((carMaker, aLong) -> carMaker))
                .doOnDispose(() -> Logger.log(LogType.DO_ON_DISPOSE, "# 생산자: 구독 해지 완료"))
                // 람다를 이용해서는 구독을 해지하는 과정을 받기 힘들어서 생성자로 만들어서 사용함
                .subscribe(new Observer<CarMaker>() {

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        // Disposable -> 구독을 해제할수 있는 함수
                        this.disposable = d;
                        this.startTime = TimeUtil.start();
                    }

                    @Override
                    public void onNext(@NonNull CarMaker carMaker) {
                        Logger.log(LogType.ON_NEXT, carMaker);
                        if (TimeUtil.getCurrentTime() - startTime > 1000L) {
                            Logger.log(LogType.PRINT, "# 소비자: 구독 해지, 1000L 초과");
                            disposable.dispose(); // 해당함수가 실행되면 "doOnDispose"가 실행됨
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Logger.log(LogType.ON_ERROR, e);
                    }

                    @Override
                    public void onComplete() {
                        Logger.log(LogType.ON_COMPLETE);
                    }

                    private Disposable disposable;
                    private long startTime;

                });

        TimeUtil.sleep(2000L);
    }
}
