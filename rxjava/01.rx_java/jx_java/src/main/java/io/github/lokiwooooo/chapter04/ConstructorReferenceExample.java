package io.github.lokiwooooo.chapter04;

import io.github.lokiwooooo.chapter04.entity.Car;

import java.util.function.Function;

public class ConstructorReferenceExample {
    public static void main(String[] args) {
        Function<String, Car> f1 = s -> new Car(s);
        Car car1 = f1.apply("콜로라도");
        System.out.println(car1.getCarName());

        Function<String, Car> f2 = Car::new;
        Car car2 = f2.apply("팰리세이드");
        System.out.println(car2.getCarName());
    }
}
