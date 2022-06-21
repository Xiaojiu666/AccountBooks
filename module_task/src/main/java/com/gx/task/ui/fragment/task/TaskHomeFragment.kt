package com.gx.task.ui.fragment.task

import android.content.Intent
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.autoservice.IActivity
import com.gx.base.base.vb.BaseVBFragment
import com.gx.data.task.Plan
import com.gx.module_task.databinding.FragmentTaskHomeBinding
import com.gx.task.getTaskData
import com.gx.task.ui.activities.TaskListActivity
import com.gx.task.ui.activities.TaskNewActivity
import com.gx.task.ui.adapter.RvPlanListAdapter
import com.gx.task.vm.TaskViewModel
import com.gx.utils.log.LogUtil
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

/**
 * A fragment representing a list of Items.
 */
@AndroidEntryPoint
class TaskHomeFragment : BaseVBFragment<FragmentTaskHomeBinding>() {

    private val viewModel: TaskViewModel by viewModels()
    private val rvTaskListAdapter = RvPlanListAdapter(null)


    override fun FragmentTaskHomeBinding.initBinding() {
        with(taskRecyclerView) {
            layoutManager = LinearLayoutManager(context)
            adapter = rvTaskListAdapter
        }
        taskFbAdd.setOnClickListener {
            startNewTaskActivity()
        }
        taskTvToady.setOnClickListener {
            val load = ServiceLoader.load(IActivity::class.java,javaClass.classLoader).toList()
            load.forEach {
                it.jump2Activity(requireContext())
            }
        }
    }


    override fun initView() {
        super.initView()
        viewModel.mPlans.observe(this) {
            LogUtil.d(TAG, "observe mTask ${it.size}")
            rvTaskListAdapter.list = it
        }
        val onItemClickListener: (view: View, position: Int) -> Unit = { view, position ->
            val plan = rvTaskListAdapter.list!![position]
            startTaskListActivity(plan)
        }
        rvTaskListAdapter.setOnItemClickListener(onItemClickListener)
    }

    override fun initData() {

    }

    private suspend fun insertData() = withContext(Dispatchers.IO) {
        viewModel.taskRepository.insertTasks(
            getTaskData()
        )
    }

//    override fun getLayoutView(inflater: LayoutInflater): View? {
//        dataBinding = FragmentTaskHomeBinding.inflate(layoutInflater)
//        return dataBinding!!.root
//    }

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