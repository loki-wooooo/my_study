package io.github.lokiwooooo.chapter03;

public class SingleJustExample {
    public static void main(String[] args) {
        Single.just(DateUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"))
                .subscribe(
                    date -> System.out::println, 
                    error -> System.err::println);
    }
}