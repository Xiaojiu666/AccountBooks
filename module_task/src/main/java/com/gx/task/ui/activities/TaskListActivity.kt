package com.gx.task.ui.activities

import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.gx.base.base.vb.BaseVBActivity
import com.gx.module_task.databinding.ActivityTaskListBinding
import com.gx.task.repository.data.Plan
import com.gx.task.repository.data.Task
import com.gx.task.ui.adapter.RvTaskListAdapter
import com.gx.task.ui.view.SectionDecoration
import com.gx.task.vm.TaskViewModel
import com.sn.libaray.log.LogUtils
import com.sn.libaray.log.TAG
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TaskListActivity : BaseVBActivity<ActivityTaskListBinding>() {

    private val viewModel: TaskViewModel by viewModels()

    private lateinit var plan: Plan

    private val rvTaskListAdapter = RvTaskListAdapter(null)

    override fun ActivityTaskListBinding.initBinding() {
        synchronized(this@TaskListActivity){

        }
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

        taskFbAdd.setOnClickListener {
            val task = Task(plan.planId)
            task.taskName = "跳绳"
            viewModel.insertTask(task)
        }
    }

    private fun initListener() {
        rvTaskListAdapter.setOnItemCheckStatusListener(object :
            RvTaskListAdapter.OnItemCheckStatusListener {
            override fun onCheckItem(view: View, position: Int) {
                var selectorData = rvTaskListAdapter.mList!![position]
                LogUtils.d(TAG, "mList before ${rvTaskListAdapter.mList.toString()}")
                if (selectorData.taskStatus == 0) {
                    selectorData.taskStatus = 1
                } else {
                    selectorData.taskStatus = 0
                }
                rvTaskListAdapter.mList!!.removeAt(position)
                rvTaskListAdapter.mList!!.add(selectorData)
                rvTaskListAdapter.notifyDataSetChanged()
                LogUtils.d(TAG, "mList after${rvTaskListAdapter.mList.toString()}")
//                viewModel.upDateTask()
            }
        })
    }


    override fun initData() {
        plan = intent.getParcelableExtra("planInfo")!!
        LogUtils.d(TAG, "plan  $plan")
        with(plan) {
            viewModel.getTaskList4PlanId(planId)
            mBinding.toolbar.title = title
        }
        viewModel.allTask.observe(this) {
            rvTaskListAdapter.list = it
        }
    }
}