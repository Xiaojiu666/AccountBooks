package com.gx.task.ui.activities

import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.gx.base.base.vb.BaseVBActivity
import com.gx.module_task.R
import com.gx.module_task.databinding.ActivityPlanNewBinding
import com.gx.task.repository.data.Plan
import com.gx.task.repository.data.Task
import com.gx.task.ui.adapter.RvTaskListAdapter
import com.gx.task.ui.adapter.RvTaskNameAdapter
import com.gx.task.vm.TaskViewModel
import com.sn.libaray.log.LogUtils
import dagger.hilt.android.AndroidEntryPoint
import org.jetbrains.anko.startActivity
import java.net.IDN

@AndroidEntryPoint
class PlanNewActivity : BaseVBActivity<ActivityPlanNewBinding>() {

    private val viewModel: TaskViewModel by viewModels()

    private val rvTaskListAdapter = RvTaskListAdapter(null)

    override fun ActivityPlanNewBinding.initBinding() {
        initActionBar(toolbar)
        ivPlanAddTask.setOnClickListener {
            if (TextUtils.isEmpty(etPlanTitle.text)) {
                Toast.makeText(
                    baseContext,
                    R.string.plan_hint_sub_title,
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }
            startActivity<TaskNewActivity>("planInfo" to viewModel.mPlanInfo)
        }
        with(recyclerView) {
            layoutManager = LinearLayoutManager(context)
            adapter = rvTaskListAdapter
        }

    }

    override fun initData() {
        super.initData()
        viewModel.getTaskList4PlanId(1L)
        viewModel.allTask.observe(this) {
            LogUtils.e(TAG, "tasks$it")
            rvTaskListAdapter.list = it
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.task_create, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.task_save -> {
                savePlan()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun savePlan() {
//        val plan = Plan("").apply {
//            planStartTime = System.currentTimeMillis()
//            planEndTime = System.currentTimeMillis()
//        }

        viewModel.insertPlan(viewModel.mPlanInfo)
    }

}