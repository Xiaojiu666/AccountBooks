package com.gx.task.ui.fragment

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.gx.accountbooks.base.BaseFragment
import com.gx.module_task.databinding.FragmentTaskHomeBinding
import com.gx.task.getTaskData
import com.gx.task.ui.activities.NewTaskActivity
import com.gx.task.ui.adapter.RvTaskListAdapter
import com.gx.task.vm.TaskViewModel
import com.gx.ui.dialog.LoadingDialog
import com.gx.utils.log.LogUtil
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * A fragment representing a list of Items.
 */
@AndroidEntryPoint
class TaskHomeFragment : BaseFragment() {

    var dataBinding: FragmentTaskHomeBinding? = null

    private val viewModel: TaskViewModel by viewModels()


    override fun initView(view: View) {
        val taskRecyclerView = dataBinding?.taskRecyclerView
        with(taskRecyclerView!!) {
            layoutManager = LinearLayoutManager(context)
            adapter = RvTaskListAdapter(null)
        }
        LogUtil.e(viewModel.toString())

        viewModel.tasks.observe(viewLifecycleOwner) {
            LogUtil.e(TAG, Thread.currentThread().name)
            loadingDialog.show(parentFragmentManager, "");
            val rvTaskListAdapter = taskRecyclerView.adapter as RvTaskListAdapter
            rvTaskListAdapter.list = it
            loadingDialog.dismiss()
            LogUtil.e(it.toString())
        }

        dataBinding!!.imageView3.setOnClickListener {
            startNewTaskActivity()
        }
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

    fun startNewTaskActivity() {
        val intent = Intent(activity, NewTaskActivity::class.java)
        startActivity(intent)
    }


}