package com.gx.task.ui.fragment.task

import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.Observer
import com.gx.accountbooks.base.BaseFragment
import com.gx.module_task.databinding.FragmentTaskDetailBinding
import com.gx.task.vm.TaskDetailViewModel

import javax.inject.Inject

 class TaskDetailFragment : BaseFragment() {

    var inflate: FragmentTaskDetailBinding? = null

    @Inject
    @JvmField
    var taskViewModel: TaskDetailViewModel? = null

    override fun initView() {
        val appContainer = (activity?.application as com.gx.task.TaskApplication).appContainer
//        taskViewModel = appContainer.taskDetailViewModel.create()
//        LogUtil.e(TAG, "TaskDetail ")
        taskViewModel!!.taskDetail.observe(this, Observer {
//            LogUtil.e(TAG, "TaskDetail ${it.toString()}")
        })
        inflate?.textView2?.setOnClickListener {
            taskViewModel!!.upDataTaskDetailInfo()
        }
    }


     override fun initData() {
     }

     override fun getLayoutView(inflater: LayoutInflater): View? {
        inflate = FragmentTaskDetailBinding.inflate(layoutInflater)
        return inflate?.root
    }
}