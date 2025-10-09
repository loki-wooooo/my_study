package io.github.lokiwooooo.section1;

import io.github.lokiwooooo.utils.LogType;
import io.github.lokiwooooo.utils.Logger;
import io.github.lokiwooooo.utils.TimeUtil;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import java.io.File;

public class SchedulerIOExample03 {
    public static void main(String[] args) {
        File[] files = new File("src/main/java/io/github/lokiwooooo/").listFiles();

        //observeOn -> 여러개를 지정할 수 있음
        Observable.fromArray(files)
                .doOnNext(file -> Logger.log(LogType.DO_ON_NEXT, "# 데이터 통지"))
                .subscribeOn(Schedulers.io()) //생산자쪽의 데이터 통지할때 thread를 지정
                .observeOn(Schedulers.computation()) //소비자에서 데이터 처리
                .filter(file -> file.isDirectory())

                .doOnNext(data -> Logger.log(LogType.DO_ON_NEXT, "# filter() 거침"))
                .observeOn(Schedulers.computation())
                .map(dir -> dir.getName())

                .doOnNext(data -> Logger.log(LogType.DO_ON_NEXT, "# map() 거침"))
                .observeOn(Schedulers.computation())

                .subscribe(dir -> Logger.log(LogType.ON_NEXT, dir));

        TimeUtil.sleep(1000L);
    }
}
