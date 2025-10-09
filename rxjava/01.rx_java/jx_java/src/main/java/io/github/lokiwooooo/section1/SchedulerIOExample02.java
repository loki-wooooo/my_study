package io.github.lokiwooooo.section1;

import io.github.lokiwooooo.utils.LogType;
import io.github.lokiwooooo.utils.Logger;
import io.github.lokiwooooo.utils.TimeUtil;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import java.io.File;

public class SchedulerIOExample02 {
    public static void main(String[] args) {
        File[] files = new File("src/main/java/io/github/lokiwooooo/").listFiles();

        Observable.fromArray(files)
                .doOnNext(file -> Logger.log(LogType.DO_ON_NEXT, file.getName()))
                .subscribeOn(Schedulers.io()) //생산자쪽의 데이터 통지할때 thread를 지정
                .observeOn(Schedulers.computation()) //소비자에서 데이터 처리
                .filter(file -> file.isDirectory())
                .map(dir -> dir.getName())
                .subscribe(dir -> Logger.log(LogType.ON_NEXT, dir));

        TimeUtil.sleep(1000L);
    }
}
