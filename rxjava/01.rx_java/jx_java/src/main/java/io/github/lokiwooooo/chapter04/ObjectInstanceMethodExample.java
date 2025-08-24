package io.github.lokiwooooo.chapter04;

import io.github.lokiwooooo.chapter04.entity.CarInventory;

import java.util.function.Function;
import java.util.function.IntUnaryOperator;
import java.util.function.UnaryOperator;

public class ObjectInstanceMethodExample {
    public static void main(String[] args) {
        final CarInventory carInventory = new CarInventory(10);

        /*
        * T: 입력 타입 (Input type)
        * R: 출력 또는 반환 타입 (Return type)
        * */
        Function<Integer, Integer> f1 = count -> carInventory.getExpectedTotalCount(count);
        int totalCount1 = f1.apply(10);
        System.out.println(totalCount1);

        Function<Integer, Integer> f2 = carInventory::getExpectedTotalCount;
        int totalCount2 = f2.apply(10);
        System.out.println(totalCount2);

        // T-> T
        // 타입과 리턴값이 같은때 사용가능함
        UnaryOperator<Integer> f3 = carInventory::getExpectedTotalCount;
        int totalCount3 = f3.apply(30);
        System.out.println(totalCount3);

        // Integer -> integer
        IntUnaryOperator f4 = carInventory::getExpectedTotalCount;
        int totalCount4 = f4.applyAsInt(50);
        System.out.println(totalCount4);
    }
}
