package io.github.lokiwooooo.chapter04;

import io.github.lokiwooooo.chapter04.entity.Car;

import java.util.function.Function;

/**
 * Class Name::instance Method 메서드 레퍼런스 예
 * */
public class ClassNameInstanceMethodExample {
    public static void main(String[] args) {
        Function<Car, String> f1 = car -> car.getCarName();
        String carName1 = f1.apply(new Car("트래버스"));
        System.out.println(carName1);

        Function<Car, String> f2 = Car::getCarName;
        String carName2 = f2.apply(new Car("팰리세이드"));
        System.out.println(carName2);
    }
}
