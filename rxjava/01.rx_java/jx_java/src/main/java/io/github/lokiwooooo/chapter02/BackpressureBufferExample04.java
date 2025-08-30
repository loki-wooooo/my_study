//package io.github.lokiwooooo.chapter02;
//
//import java.util.concurrent.TimeUnit;
//
//import io.reactivex.Flowable;
//import io.reactivex.schedulers.Schedulers;
//
//public class BackpressureBufferExample03 {
//
//    public static void main(String[] args) throws InterruptedException {
//
//        System.out.println("start" + System.currentTimeMillis());
//
//        Flowable.interval(1L, TimeUnit.MICROSECONDS)
//                //interval에서 호출 하는 콜백 함수 doOnNext
//                .doOnNext(data -> System.out.println(data))
//                //요청데이터를 갖고있다가 최신 데이터 기준으로 넣음
//                .onBackpressureLatest()
//                //스케줄러 챕터에서 이야기 다시함(데이터를 처리하는 스레드를 분리할 수 있음)
//                // buffer의 용량 X, 소비자에서 요청하는 buffer의 개수
//                .observeOn(Schedulers.computation(), false, 1)
//                .subscribe(data -> {
//                    System.out.println("데이터 처리중...");
//                    TimeUnit.SECONDS.sleep(1L);
//                    System.out.println(data);
//                },
//                        error -> System.err.println("에러"),
//                        () -> System.out.println("OK"));
//        try {
//            Thread.sleep(2000L);
//        } catch (InterruptedException e) {
//            Thread.currentThread().interrupt();
//        }
//    }
//}
