package io.github.lokiwooooo.chapter03;

import java.util.Date;

import io.reactivex.Maybe;

public class MaybeJustExample {

    public static void main(String[] args) {
        // Maybe.just(DateUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"))
        //         .subscribe(
        //             date -> System.out::println, 
        //             error -> System.err::println
        //             () -> System.out.println("완료")
        //             );

        //데이터 통지없이 완료
        Maybe.empty()
                .subscribe(
                        data -> System.out::println,
                        error -> System.err::println,
                        () -> System.out.println("완료")
                );
    }
}
