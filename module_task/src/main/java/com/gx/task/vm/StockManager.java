package com.gx.task.vm;

public class StockManager {

    private SimplePriceListener simplePriceListener;


    public void requestPriceUpdates(SimplePriceListener simplePriceListener) {
        this.simplePriceListener = simplePriceListener;
    }

    public void removeUpdates() {
        if (simplePriceListener != null) {
            simplePriceListener = null;
        }
    }
}
