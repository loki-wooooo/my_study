package io.github.lokiwooooo.chapter01;

import io.reactivex.processors.PublishProcessor;

public class HotPublischerExample {
    /*
     * HotPublisher
     * processor
     * subject
     * ex) 잡지로 생각하면 편함
     * */
    public static void main(String[] args) {
        PublishProcessor<Integer> processor = PublishProcessor.create();
        processor.subscribe(data -> System.out.println("구독자1: " + data));
        processor.onNext(1);
        processor.onNext(3);

        processor.subscribe(data -> System.out.println("구독자2: " + data));
        processor.onNext(5);
        processor.onNext(7);

        processor.onComplete();
    }
}
