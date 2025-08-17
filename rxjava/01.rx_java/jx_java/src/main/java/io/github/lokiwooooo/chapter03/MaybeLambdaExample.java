package io.github.lokiwooooo.chapter03;

import io.reactivex.Maybe;

public class MaybeLambdaExample {
    public static void main(String[] args) {
        Maybe<String> maybe = Maybe.create(
            // emitter -> emitter.onSuccess(DateUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"))
            emitter -> emitter.onComplete()
            );

        maybe.subscribe(
            data -> System.out.println("# 날짜 시간: " + data),
            error -> System.err.println("# 에러: " + error.getMessage()),
            () -> System.out.println("# 완료")
        );
    }
}
