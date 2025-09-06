package io.github.lokiwooooo.chapter05.map;

import io.github.lokiwooooo.common.Searcher;
import io.reactivex.Observable;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ObservableSwitchMapExample02 {
    public static void main(String[] args) throws Exception {
        Searcher searcher = new Searcher();
        // 사용자가 입력하는 검색어라고 가정한다.
        final List<String> keywords = Arrays.asList("M", "Ma", "Mal", "Malay");

        Observable.interval(100L, TimeUnit.MILLISECONDS)
                .take(4)
                .switchMap(data -> { /** concatMap을 사용했기때문에 매번 모든 키워드 검색 결과를  다 가져온다.*/
                    String keyword = keywords.get(data.intValue()); // 데이터베이스에서 조회한다고 가정한다.

                    return Observable.just(searcher.search(keyword))
                            .doOnNext(notUse -> System.out.println("================================================================="))
                            .delay(1000L, TimeUnit.MILLISECONDS);
                })
                .flatMap(resultList -> Observable.fromIterable(resultList))
                .subscribe(
                        data -> System.out.println(data),
                        error -> {},
                        () -> {
//                            TimeUtil.end();
//                            TimeUtil.takeTime();
                        }
                );


//        TimeUtil.sleep(6000L);
    }
}
