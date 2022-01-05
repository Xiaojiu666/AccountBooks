package com.gx.task.ui.activities

import android.view.View
import com.gx.task.di.DaggerTestContainer
import com.gx.task.di.TestRepository
import com.gx.base.base.BaseAppCompatActivity
import com.gx.module_task.R
import com.tencent.mars.xlog.Log
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TaskActivity : BaseAppCompatActivity() {

    @Inject
    lateinit var testRepository: TestRepository
    override fun initData() {
    }

    override fun initView() {
        Log.e(TAG, "testRepository name : ${testRepository.returnName()}")
        Log.e(TAG,
            "testRepository.remote name : ${testRepository.testRemoteDataSource.returnName()}"
        )
    }
    override fun getLayoutView(): View = createView(R.layout.activity_task)

}