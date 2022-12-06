package com.gx.task.ui.activities

import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.gx.base.base.vb.BaseVBActivity
import com.gx.task.repository.data.Plan
import com.gx.task.repository.data.Task
import com.gx.module_task.databinding.ActivityTaskListBinding
import com.gx.task.ui.view.SectionDecoration
import com.gx.task.ui.adapter.RvTaskListAdapter
import com.gx.task.vm.TaskViewModel

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TaskListActivity : BaseVBActivity<ActivityTaskListBinding>() {

    private val viewModel: TaskViewModel by viewModels()
    private lateinit var plan: Plan

    private val rvTaskListAdapter = RvTaskListAdapter(null)

    override fun ActivityTaskListBinding.initBinding() {
        initActionBar(toolbar)
        initListener()
        with(taskRvList) {
            layoutManager = LinearLayoutManager(context)
            adapter = rvTaskListAdapter
        }


        val sectionDecoration =
            SectionDecoration(baseContext, object : SectionDecoration.DecorationCallback {
                override fun getGroupId(position: Int): Task {
                    return rvTaskListAdapter.mList?.get(position)!!
                }
            })
        taskRvList.addItemDecoration(sectionDecoration)
    }

    private fun initListener() {
        rvTaskListAdapter.setOnItemCheckStatusListener(object :
            RvTaskListAdapter.OnItemCheckStatusListener {
            override fun onCheckItem(view: View, status: Int, position: Int) {
                viewModel.upDateTask(rvTaskListAdapter.mList!![position])
            }
        })
    }


    override fun initData() {
        plan = intent.getParcelableExtra("plan")!!
//        LogUtil.d("plan  $plan")
        with(plan) {
            viewModel.getTaskList4PlanId(planId)
            mBinding.toolbar.title = title
        }
        viewModel.allTask.observe(this) {
//            LogUtil.e(TAG, "mTasks : $it")
            rvTaskListAdapter.list = it
        }
    }
}