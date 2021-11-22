package com.gx.task.ui.activities

import android.app.Person
import android.os.Bundle
import com.gx.base.base.VBBaseActivity
import com.gx.data.task.Task
import com.gx.module_task.databinding.ActivityTaskNewBinding
import com.gx.utils.log.LogUtil
import com.tencent.mars.xlog.Log

class NewTaskVbActivity : VBBaseActivity<ActivityTaskNewBinding>() {

    override fun ActivityTaskNewBinding.initBinding() {
        LogUtil.e("TAG", "${textView6.text}")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Task("AAA").test()
    }

    fun Task.test(){
        LogUtil.e("TAG", "title ${title} task")
    }
}