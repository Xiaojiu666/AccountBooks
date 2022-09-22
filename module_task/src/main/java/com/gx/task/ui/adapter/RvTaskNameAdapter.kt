package com.gx.task.ui.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gx.base.base.BaseRecyclerViewAdapter
import com.gx.module_task.R
import com.gx.data.task.Plan
import com.gx.module_task.databinding.ItemDialogTitleBinding



class RvTaskNameAdapter(mList: MutableList<Plan>?) :
    BaseRecyclerViewAdapter<Plan>(mList) {

    private var dataBinding: ItemDialogTitleBinding? = null

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val taskListInfo = mList!![position]
//        LogUtil.d("taskListInfo${taskListInfo}")
        dataBinding!!.tvDialogTitle.text = taskListInfo.title
    }

    inner class TaskListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        init {
//            setOnItemClickListener { view, posiion ->
//                val task = mList!![posiion]
//                LogUtil.d("task : ${task}")
//            }
        }
    }

    override fun getItemViewHolder(parent: ViewGroup?): RecyclerView.ViewHolder {
        dataBinding = ItemDialogTitleBinding.bind(getView(parent!!, R.layout.item_dialog_title))
        return TaskListViewHolder(dataBinding!!.root)
    }
}