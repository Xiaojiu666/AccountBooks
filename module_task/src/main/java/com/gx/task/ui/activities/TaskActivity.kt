package com.gx.task.ui.activities

import android.view.View
import com.gx.base.base.BaseAppCompatActivity
import com.gx.module_task.R
import com.gx.task.di.TestRepository
import com.gx.task.test.ProxyTest
import com.gx.task.test.TimeInterfaces
import com.gx.utils.log.LogUtil
import com.tencent.mars.xlog.Log
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.*
import java.net.Proxy
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@AndroidEntryPoint
class TaskActivity : BaseAppCompatActivity() {

    @Inject
    lateinit var testRepository: TestRepository
    override fun initData() {
    }

    override fun initView() {
        val testFun = ProxyTest.create(TimeInterfaces::class.java).testFun(1000)
        LogUtil.d(TAG, "initView , ${testFun}")
        val client = OkHttpClient.Builder()
            .proxy(Proxy.NO_PROXY)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()

        val retrofit = Retrofit.Builder()
            .client(client)
            .baseUrl("http://120.0.0.1")
            .build()
//
        retrofit.create(DownloadAndUploadFileService::class.java)
//            .download()!!
//            .enqueue(object : Callback<ResponseBody?> {
//                override fun onResponse(
//                    call: Call<ResponseBody?>,
//                    response: Response<ResponseBody?>
//                ) {
//                }
//
//                override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
//                }
//
//            })
//
//        Log.e(TAG, "testRepository name : ${testRepository.returnName()}")
//        Log.e(
//            TAG,
//            "testRepository.remote name : ${testRepository.testRemoteDataSource.returnName()}"
//        )
    }

    internal interface DownloadAndUploadFileService {
        @PUT
        @Headers("Content-Type:application/octet-stream")
        fun upload(@Url url: String?, @Body route: RequestBody?): Call<ResponseBody?>?

        @Streaming
        @GET
        fun download(): Call<ResponseBody?>?
    }

    override fun getLayoutView(): View = createView(R.layout.activity_task)

}