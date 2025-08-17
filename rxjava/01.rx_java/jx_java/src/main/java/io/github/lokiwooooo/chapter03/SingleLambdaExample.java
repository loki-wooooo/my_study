package io.github.lokiwooooo.chapter03;

public class SingleLambdaExample {

    public static void main(String[] args) {
        Single<String> single = Single.create(emitter -> emitter.onSuccess(DateUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss")));

        single.subscribe(
                data -> System.out.println("# 날짜 시간: " + data),
                error -> System.err.println("# 에러: " + error.getMessage())
        );

    }
}
