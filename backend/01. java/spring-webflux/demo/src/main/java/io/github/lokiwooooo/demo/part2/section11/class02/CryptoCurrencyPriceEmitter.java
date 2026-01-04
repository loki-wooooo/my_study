package io.github.lokiwooooo.demo.part2.section11.class02;

import io.github.lokiwooooo.demo.part2.section11.class01.SampleData;

public class CryptoCurrencyPriceEmitter {
    private CryptoCurrencyPriceListener listener;

    public void setListener(CryptoCurrencyPriceListener listener) {
        this.listener = listener;
    }

    public void flowInto() {
        listener.onPrice(SampleData.btcPrices);
    }
    public void complete() {
        listener.onComplete();
    }
}