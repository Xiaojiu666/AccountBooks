package com.gx.task

import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.gx.accountbooks.base.BaseFragment
import com.gx.module_task.databinding.FragmentTaskHomeBinding
import com.gx.task.di.demo.Person
import com.gx.task.ui.adapter.RvTaskListAdapter
import com.gx.task.vm.TaskViewModel
import com.gx.utils.log.LogUtil
import com.tencent.mars.xlog.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * A fragment representing a list of Items.
 */
class TaskHomeFragment : BaseFragment() {
    var dataBinding: FragmentTaskHomeBinding? = null

    private val viewModel: TaskViewModel by viewModels()

    override fun initView(view: View) {

        val taskRecyclerView = dataBinding?.taskRecyclerView
        with(taskRecyclerView!!) {
            layoutManager = LinearLayoutManager(context)
            adapter = RvTaskListAdapter(getTaskData())
        }
        LogUtil.e(viewModel.name)
        viewModel.tasks.observe(viewLifecycleOwner) {
            val rvTaskListAdapter = taskRecyclerView.adapter as RvTaskListAdapter
            rvTaskListAdapter.list = it
        }

        dataBinding!!.imageView3.setOnClickListener {
            GlobalScope.launch {
                insertData()
            }  //  在 UI 线程开始
        }
    }

    private suspend fun insertData() = withContext(Dispatchers.IO) {
        viewModel.taskRepository.createTasks(
            getTaskData()
        )
    }

    override fun getLayoutView(inflater: LayoutInflater): View? {
        dataBinding = FragmentTaskHomeBinding.inflate(layoutInflater)
        return dataBinding!!.root
    }


}