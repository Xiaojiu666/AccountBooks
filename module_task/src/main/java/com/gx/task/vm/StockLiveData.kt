package com.gx.task.vm

import androidx.lifecycle.LiveData

class StockLiveData : LiveData<Int>() {

    private val stockManager = com.gx.task.vm.StockManager()

    private val listener = com.gx.task.vm.SimplePriceListener {
        value = it
    }

    override fun onActive() {
        stockManager.requestPriceUpdates(listener)
    }

    override fun onInactive() {
        stockManager.removeUpdates()
    }
}