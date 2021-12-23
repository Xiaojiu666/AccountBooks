package com.gx.task.vm

import androidx.lifecycle.LiveData

class StockLiveData : LiveData<Int>() {

    private val stockManager = StockManager()

    private val listener = SimplePriceListener {
        value = it
    }

    override fun onActive() {
        stockManager.requestPriceUpdates(listener)
    }

    override fun onInactive() {
        stockManager.removeUpdates()
    }
}