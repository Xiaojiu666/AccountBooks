package com.gx.accountbooks.Thred

import com.gx.utils.log.LogUtil

class MyThread(name: String) : Thread() {
    val TAG = " MyThread "

    override fun run() {
        for (i in 0..10){
            LogUtil.d(TAG, Thread.currentThread().name + "= $i")
        }
//        repeat(10) {
//            LogUtil.d(TAG, Thread.currentThread().name)
//        }
    }
}