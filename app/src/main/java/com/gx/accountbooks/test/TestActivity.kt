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

class TestActivity : BaseAppCompatActivity() {
    override fun init() {}
    override fun initView() {
        //test()
//        testRepeat()
//        LogUtil.d(TAG, "Thread Name ${Thread.currentThread().name}")
//        runBlocking {
//            delay(5000)
//            LogUtil.d(TAG, "Thread Name ${Thread.currentThread().name}")
//        }
//        LogUtil.d(TAG, "Thread Name ${Thread.currentThread().name}")
//        GlobalScope.launch(Dispatchers.IO) {
//            delay(500)
//            LogUtil.d(TAG, "Thread Name1 ${Thread.currentThread().name}")
//            LogUtil.d(TAG, "Thread Name1 ${Thread.currentThread().id}")
        LogUtil.d(TAG, "launch before ${Thread.currentThread().name}")
        GlobalScope.launch {
            LogUtil.d(TAG, "launch TreadName =  ${Thread.currentThread().name}")
            val async = async {
                delay(5000)
                LogUtil.d(TAG, "async TreadName = ${Thread.currentThread().name}")
                "async result"
            }
            LogUtil.d(TAG, "async after ")
            LogUtil.d(TAG, "async after , async result${async.await()} ")
            LogUtil.d(
                TAG, "async after TreadName = ${Thread.currentThread().name} , async = $async"
            )
        }
        LogUtil.d(TAG, "launch after ${Thread.currentThread().name}")

//        GlobalScope.async {
//            delay(5000)
//            LogUtil.d(TAG, "launch " + Thread.currentThread().name)
//            "aapt"
//        }
//        LogUtil.d(TAG, "launch after " + Thread.currentThread().name)

//        GlobalScope.launch {
//            val launchJob = launch {
//                LogUtil.d("launch", "启动一个协程")
//            }
//            LogUtil.d("launchJob", "$launchJob")
//            val asyncJob = async {
//                delay(5000)
//                LogUtil.d("async", "启动一个协程")
//                "我是async返回值"
//            }
////            LogUtil.d("asyncJob.await", ":${asyncJob.await()}")
//            LogUtil.d("asyncJob", "$asyncJob")
//            LogUtil.d("asyncJob", "AAAAAAA")
//        }
//        val asyncJob = GlobalScope.async{
//            LogUtil.d("async", "启动一个协程")
//            "我是返回值"
//        }
//        LogUtil.d("asyncJob", "$asyncJob")
//
//        val runBlockingJob = runBlocking {
//            LogUtil.d(TAG, "runBlocking启动一个协程")
//            "我是runBlockingJob协程的返回值"
//        }
//        LogUtil.d(TAG, "runBlockingJob = $runBlockingJob")
//        GlobalScope.launch(Dispatchers.IO) {
//                LogUtil.d(TAG, "Thread Name1_1 ${Thread.currentThread().name}")
//                LogUtil.d(TAG, "Thread Name1_1 ${Thread.currentThread().id}")
//            }
//
//        GlobalScope.launch(Dispatchers.Default) {
//                LogUtil.d(TAG, "Thread Name1_2 ${Thread.currentThread().name}")
//                LogUtil.d(TAG, "Thread Name1_2 ${Thread.currentThread().id}")
//            }
//
//        GlobalScope.launch(Dispatchers.Unconfined) {
//                LogUtil.d(TAG, "Thread Name1_3 ${Thread.currentThread().name}")
//                LogUtil.d(TAG, "Thread Name1_3 ${Thread.currentThread().id}")
//            }
//        GlobalScope.launch(Dispatchers.Main) {
//                LogUtil.d(TAG, "Thread Name1_4 ${Thread.currentThread().name}")
//                LogUtil.d(TAG, "Thread Name1_4 ${Thread.currentThread().id}")
//            }
//        }

//        GlobalScope.launch(Dispatchers.IO) {
////            delay(500)
//            LogUtil.d(TAG, "Thread Name2 ${Thread.currentThread().name}")
//            LogUtil.d(TAG, "Thread Name2 ${Thread.currentThread().id}")
//
//            launch(Dispatchers.IO) {
//                LogUtil.d(TAG, "Thread Name2_1 ${Thread.currentThread().name}")
//                LogUtil.d(TAG, "Thread Name2_1 ${Thread.currentThread().id}")
//            }
//
//            launch(Dispatchers.Default) {
//                LogUtil.d(TAG, "Thread Name1_2 ${Thread.currentThread().name}")
//                LogUtil.d(TAG, "Thread Name2_2 ${Thread.currentThread().id}")
//            }
//
//            launch(Dispatchers.Unconfined) {
//                LogUtil.d(TAG, "Thread Name2_3 ${Thread.currentThread().name}")
//                LogUtil.d(TAG, "Thread Name2_3 ${Thread.currentThread().id}")
//            }
//            launch(Dispatchers.Main) {
//                LogUtil.d(TAG, "Thread Name2_4 ${Thread.currentThread().name}")
//                LogUtil.d(TAG, "Thread Name2_4 ${Thread.currentThread().id}")
//            }
//        }


//        GlobalScope.launch(Dispatchers.Unconfined) {
//            delay(5000)
//            LogUtil.d("runBlocking Unconfined", "Thread Name ${Thread.currentThread().name}")
//            LogUtil.d("runBlocking Unconfined", "Thread Name ${Thread.currentThread().id}")
//        }
//        GlobalScope.launch(Dispatchers.Default) {
//            delay(5000)
//            LogUtil.d("runBlocking Default", "Thread Name ${Thread.currentThread().name}")
//            LogUtil.d("runBlocking Default", "Thread Name ${Thread.currentThread().id}")
//        }
//        GlobalScope.launch(Dispatchers.IO) {
//            delay(5000)
//            LogUtil.d("runBlocking IO", "Thread Name ${Thread.currentThread().name}")
//            LogUtil.d("runBlocking IO", "Thread Name ${Thread.currentThread().id}")
//        }
//        LogUtil.d(TAG, "Thread Name ${Thread.currentThread().name}")


        editTextTextPersonName.text = "初始化"

//        MyThread("test").start()
//        Thread(MyRunnable()).start()
//
//        editTextTextPersonName.setOnClickListener {
//            ARouter.getInstance().build(AppConfig.ACTIVITY_LOGIN).navigation()
//        }

//
//        runBlocking {
//            for (i in 0..10) {
//                LogUtil.d("runBlocking1 ", Thread.currentThread().name + "= $i")
//
//            }
//        }
//
//        runBlocking {
//            for (i in 1..3) {
//                LogUtil.d(TAG, "协程任务1打印第$i 次，时间: " + System.currentTimeMillis())
//            }
//            delay(1000)
//            for (i in 0..1000) {
//                editTextTextPersonName.text = "第 ($i)个数"
//                LogUtil.d("runBlocking2 ", Thread.currentThread().name + "= $i")
//            }
//        }
//
//        LogUtil.e(TAG, "初始化 ThreadName${Thread.currentThread().name}")
//        editTextTextPersonName.text = "初始化"

//        LogUtil.d("AA", "协程初始化开始，时间: " + System.currentTimeMillis())
//
//       runBlocking {
//            LogUtil.d("AA", "ThreadName" + Thread.currentThread().name)
//            LogUtil.d("AA", "协程初始化完成，时间: " + System.currentTimeMillis())
//            for (i in 1..3) {
//                LogUtil.d("AA", "协程任务1打印第$i 次，时间: " + System.currentTimeMillis())
//            }
//            delay(500)
//            for (i in 1..3) {
//                LogUtil.d("AA", "协程任务2打印第$i 次，时间: " + System.currentTimeMillis())
//            }
//        }
////
//        LogUtil.d("AA", "主线程 sleep ，时间: " + System.currentTimeMillis())
//        Thread.sleep(1000)
//        LogUtil.d("AA", "主线程运行，时间: " + System.currentTimeMillis())

//        for (i in 1..3) {
//            LogUtil.d("AA", "主线程打印第$i 次，时间: " + System.currentTimeMillis())
//        }
//        GlobalScope.launch(Dispatchers.Unconfined) {
//            LogUtil.e(TAG, "GlobalScope ThreadName${Thread.currentThread().name}")
//            getMessageFromNetwork()
////            LogUtil.e(TAG, "TAG${getMessageFromNetwork()}")
//        }
//        runOnUiThread {
//            Thread {
//                var name = ""
//                LogUtil.e(TAG, "ThreadName${Thread.currentThread().name}")
//                for (i in 0..1000000) {
//                    //这里模拟一个耗时操作
//                }
//                name = TAG
//            }.start()
//        }

    }

    override fun getLayoutView(): View = View.inflate(baseContext, R.layout.activity_test, null)


    suspend fun getMessageFromNetwork(): String {
        var name = ""
        for (i in 0..1000000) {
            withContext(Dispatchers.Main) {
                LogUtil.e(TAG, "ThreadName${Thread.currentThread().name}")
                //这里模拟一个耗时操作
                editTextTextPersonName.text = "第 ($i)个数"
                Thread.sleep(1000)
            }
            name = TAG
        }
        return name
    }



    fun test() {
        val person = Person("gx", "12345678@qq.com")
        val let = person.let {
            LogUtil.e(TAG, "name ${it.name}  ,email ${it.email}")
            "aaa"
        }
        LogUtil.e(TAG, "let = ${let}")


        val with = with(person) {
            LogUtil.e(TAG, "name $name  ,email $email")
            "aaa"
        }
        LogUtil.e(TAG, "with = ${with}")

        val run = person.run {
            LogUtil.e(TAG, "name $name  ,email $email")
            "aaa"
        }
        LogUtil.e(TAG, "run = ${run}")

        val also = person.also {
            LogUtil.e(TAG, "name ${it.name}  ,email ${it.email}")
            "aaa"
        }
        LogUtil.e(TAG, "also = ${also}")

        val apply = person.apply {
            LogUtil.e(TAG, "name ${name}  ,email ${email}")
            "aaa"
        }
        LogUtil.e(TAG, "apply = ${apply}")
    }


    fun testRepeat() {
        repeat(10) {
            LogUtil.e(TAG, "apply = ${it} + ${Thread.currentThread().name}")
        }
    }


}