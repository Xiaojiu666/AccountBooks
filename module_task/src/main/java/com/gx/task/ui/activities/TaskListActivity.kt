package com.gx.task.ui.activities

import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.gx.base.base.BaseVBActivity
import com.gx.data.task.Plan
import com.gx.module_task.databinding.ActivityTaskListBinding
import com.gx.task.ui.adapter.RvTaskListAdapter
import com.gx.task.vm.TaskViewModel
import com.gx.utils.log.LogUtil
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TaskListActivity : BaseVBActivity<ActivityTaskListBinding>() {

    private val viewModel: TaskViewModel by viewModels()
    private lateinit var plan: Plan

    private val rvTaskListAdapter = RvTaskListAdapter(null)
    private val rvTaskListAdapterUn = RvTaskListAdapter(null)

    override fun ActivityTaskListBinding.initBinding() {
        initActionBar(toolbar)
        initListener()
        with(taskRvList) {
            layoutManager = LinearLayoutManager(context)
            rvTaskListAdapter.mRecyclerView = this
            adapter = rvTaskListAdapter
        }
    }

    private fun initListener() {
        mBinding.taskTvCompleteUn.setOnClickListener {
            viewModel.upDateTaskList(rvTaskListAdapter.list!!)
        }
    }


    override fun initData() {
        plan = intent.getParcelableExtra("plan")!!
        LogUtil.d("plan  $plan")
        with(plan) {
            viewModel.getTaskList4PlanId(planId)
            mBinding.toolbar.title = title
        }
        viewModel.mTasks.observe(this) {
            LogUtil.e(TAG, "mTasks : $it")
            rvTaskListAdapter.list = it
            rvTaskListAdapterUn.list = it
        }
    }

    override fun onPause() {
        super.onPause()
        viewModel.upDateTaskList(rvTaskListAdapter.list!!)
        LogUtil.d("onDestroy ${rvTaskListAdapter.list.toString()}")
    }
}