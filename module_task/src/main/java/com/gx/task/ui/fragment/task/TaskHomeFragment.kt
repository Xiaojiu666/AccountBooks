package com.gx.task.ui.fragment.task

import android.Manifest
import android.content.Intent
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.gx.base.base.vb.BaseVBFragment
import com.gx.data.task.Plan
import com.gx.module_task.databinding.FragmentTaskHomeBinding
import com.gx.task.ui.activities.TaskListActivity
import com.gx.task.ui.activities.TaskNewActivity
import com.gx.task.ui.adapter.RvPlanListAdapter
import com.gx.task.vm.TaskViewModel
import com.gx.utils.log.LogUtil
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


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
//            getPermission()
            getTime()
//            val load = ServiceLoader.load(IActivity::class.java,javaClass.classLoader).toList()
//            LogUtil.d(TAG, "load ${load.size}")
//            load.forEach {
//                it.jump2Activity(requireContext())
//            }
        }
    }

    fun getTime() {
        try {
            Thread.sleep(3000);
        } catch (e: InterruptedException) {
            e.printStackTrace();
        }
        Log.i("handleGetTime", "执行了getTime方法: ");
    }

//    @AndroidPermission(permissions = [Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA])
    fun getPermission() {
        Log.i("AndroidPermissionAspect", "获取权限成功了: ")
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
        GlobalScope.launch(Dispatchers.IO) {
            insertData()
        }
    }

    override fun initData() {

    }

    private suspend fun insertData() = withContext(Dispatchers.IO) {
//        viewModel.taskRepository.insertPlans(
//            getPlanData()
//        )
//        viewModel.taskRepository.insertTasks(
//            getTaskData()
//        )
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