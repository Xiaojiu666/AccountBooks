package com.gx.task.ui.activities

import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import com.gx.base.base.BaseAppCompatActivity
import com.gx.module_task.R
import kotlinx.android.synthetic.main.activity_task_new.*

class NewTaskActivity : BaseAppCompatActivity() {
    override fun init() {
    }

    override fun initView() {
        initActionrBar(toolbar)
    }

    override fun getLayoutView(): View {
        return createView(R.layout.activity_task_new)
    }


}