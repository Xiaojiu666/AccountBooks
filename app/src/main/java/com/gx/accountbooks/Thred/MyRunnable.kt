package com.gx.accountbooks.Thred

import com.gx.utils.log.LogUtil

class MyRunnable : Runnable {
    val TAG = " MyRunnable "

    override fun run() {
        for (i in 0..10){
            LogUtil.d(TAG, Thread.currentThread().name + "= $i")
        }

    }
}