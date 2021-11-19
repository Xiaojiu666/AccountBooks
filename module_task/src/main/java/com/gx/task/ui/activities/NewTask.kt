package com.gx.task.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.gx.module_task.R
import com.gx.module_task.databinding.ActivityTaskNewBinding
import com.gx.utils.log.LogUtil

class NewTask : AppCompatActivity() {

    private lateinit var mBinding: ActivityTaskNewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding =
            DataBindingUtil.setContentView<ActivityTaskNewBinding>(this, R.layout.activity_task_new)
        LogUtil.e("TAG", "${mBinding.textView6.text}")
    }
}