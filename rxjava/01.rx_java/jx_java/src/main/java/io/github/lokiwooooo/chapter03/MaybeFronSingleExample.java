package io.github.lokiwooooo.chapter03;

import java.util.Date;

import io.reactivex.Maybe;
import io.reactivex.Single;

public class MaybeFronSingleExample {
    public static void main(String[] args) {
        Single<String> single = Single.just(DateUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
        Maybe.fromSingle(single)
                .subscribe(
                    data -> System.out.println("실행"),
                    error -> System.out.println("에러"),
                    () -> System.out.println("완료")
                );
    }
}
