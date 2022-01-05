package com.gx.task.ui.fragment

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.gx.accountbooks.base.BaseFragment
import com.gx.data.task.Plan
import com.gx.module_task.databinding.FragmentTaskHomeBinding
import com.gx.task.getTaskData
import com.gx.task.ui.activities.TaskActivity
import com.gx.task.ui.activities.TaskListActivity
import com.gx.task.ui.activities.TaskNewActivity
import com.gx.task.ui.adapter.RvPlanListAdapter
import com.gx.task.vm.TaskViewModel
import com.gx.utils.log.LogUtil
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * A fragment representing a list of Items.
 */
@AndroidEntryPoint
class TaskHomeFragment : BaseFragment() {

    var dataBinding: FragmentTaskHomeBinding? = null

    private val viewModel: TaskViewModel by viewModels()
    private val rvTaskListAdapter = RvPlanListAdapter(null)

    override fun initView(view: View) {
        val taskRecyclerView = dataBinding?.taskRecyclerView
        with(taskRecyclerView!!) {
            layoutManager = LinearLayoutManager(context)
            adapter = rvTaskListAdapter
        }
        viewModel.mPlans.observe(this) {
            LogUtil.d(TAG, "observe mTask ${it.size}")
            rvTaskListAdapter.list = it
        }
        dataBinding!!.taskFbAdd.setOnClickListener {
            startNewTaskActivity()
        }
        val onItemClickListener: (view: View, position: Int) -> Unit = { view, position ->
            val plan = rvTaskListAdapter.list!![position]
            startTaskListActivity(plan)
        }
        rvTaskListAdapter.setOnItemClickListener(onItemClickListener)
    }

    private suspend fun insertData() = withContext(Dispatchers.IO) {
        viewModel.taskRepository.insertTasks(
            getTaskData()
        )
    }

    override fun getLayoutView(inflater: LayoutInflater): View? {
        dataBinding = FragmentTaskHomeBinding.inflate(layoutInflater)
        return dataBinding!!.root
    }

    private fun startNewTaskActivity() {
        val intent = Intent(activity, TaskNewActivity::class.java)
        startActivity(intent)
    }

    private fun startTaskListActivity(plan: Plan) {
        LogUtil.d("startTaskListActivity plan {$plan}")
        val intent = Intent(activity, TaskListActivity::class.java)
        intent.putExtra("plan", plan)
        startActivity(intent)
    }


}