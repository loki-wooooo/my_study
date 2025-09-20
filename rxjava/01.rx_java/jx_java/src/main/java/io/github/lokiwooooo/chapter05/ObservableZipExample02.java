package io.github.lokiwooooo.chapter05;

import io.github.lokiwooooo.common.SampleData;
import io.reactivex.Observable;

import java.util.Arrays;
import java.util.Collections;

public class ObservableZipExample02 {
    public static void main(String[] args) throws Exception {

        Observable<Integer> observable1 = Observable.fromIterable(SampleData.seoulPM10List);
        Observable<Integer> observable2 = Observable.fromIterable(SampleData.busanPM10List);
        Observable<Integer> observable3 = Observable.fromIterable(SampleData.incheonPM10List);

        Observable<Integer> observable4 = Observable.range(1, 24);

        Observable.zip(observable1, observable2, observable3, observable4,
                        (num1, num2, num3, hour) -> hour + "시: " +  Collections.max(Arrays.asList(num1, num2, num3)))
                .subscribe(System.out::println);

        Thread.sleep(1000L);
    }
}
