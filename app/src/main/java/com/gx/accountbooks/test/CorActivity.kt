package com.gx.accountbooks.test

import android.view.View
import com.alibaba.android.arouter.launcher.ARouter
import com.gx.accountbooks.R
import com.gx.accountbooks.Thred.MyRunnable
import com.gx.accountbooks.Thred.MyThread
import com.gx.base.addTest
import com.gx.base.config.AppConfig
import com.gx.base.base.BaseAppCompatActivity
import com.gx.utils.log.LogUtil
import kotlinx.android.synthetic.main.activity_test.*
import kotlinx.coroutines.*

class CorActivity : BaseAppCompatActivity() {
    override fun init() {}
    override fun initView() {
//        LogUtil.d(TAG, "initView start TreadName =  ${Thread.currentThread().name}")
//        GlobalScope.launch {
//            LogUtil.d(TAG, "loadFile start TreadName =  ${Thread.currentThread().name}")
//            fetchTwoDocs()
//            LogUtil.d(TAG, "loadFile end after =  ${Thread.currentThread().name}")
//        }
        GlobalScope.launch {
            fetchTwoDocs()
        }

//        GlobalScope.async {
//
//        }
    }

    override fun getLayoutView(): View = View.inflate(baseContext, R.layout.activity_test, null)


    suspend fun delayFun() {
        LogUtil.d(TAG)
    }


    suspend fun loadFile(target: Int) {
        LogUtil.d(TAG,"target ${target} start")
        delay(5000 + target.toLong())
        LogUtil.d(TAG,"target ${target} end")
    }

    suspend fun fetchTwoDocs() {
        coroutineScope {
            launch { loadFile(1) }
            async { loadFile(1000) }
        }
    }
}