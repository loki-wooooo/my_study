//package io.github.lokiwooooo.chapter03;
//
//import java.util.Date;
//
//import io.reactivex.Single;
//import io.reactivex.SingleEmitter;
//import io.reactivex.SingleObserver;
//import io.reactivex.SingleOnSubscribe;
//import io.reactivex.disposables.Disposable;
//
//public class SingleCreateExample {
//    public static void main(String[] args) {
//        Single<String> single = Single.create(new SingleOnSubscribe<String>() {
//            @Override
//            public void subscribe(SingleEmitter<String> emitter) throws Exception {
//                emitter.onSuccess(DateUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
//            }
//        });
//
//        single.subscribe(new SingleObserver<String>(){
//            @Override
//            public void onSubscribe(Disposable d) {
//                //아무것도 하지 않음
//            }
//
//            @Override
//            public void onSuccess(String t) {
//                System.out.println("# 날짜 시간: " + t);
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                System.err.println("# 에러: " + e.getMessage());
//            }
//        });
//    }
//}
