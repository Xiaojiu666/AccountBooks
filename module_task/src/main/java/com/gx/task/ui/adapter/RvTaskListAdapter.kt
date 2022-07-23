package com.gx.task.ui.adapter

import android.graphics.Paint
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gx.base.base.BaseRecyclerViewAdapter
import com.gx.data.task.Task
import com.gx.module_task.R
import com.gx.module_task.databinding.ItemTaskListBinding
import com.gx.module_task.databinding.ItemTaskListCompleteBinding
import com.gx.utils.date.toDateStr
import com.gx.utils.log.LogUtil


class RvTaskListAdapter(mList: MutableList<Task>?) :
    BaseRecyclerViewAdapter<Task>(mList) {

    companion object {
        const val TYPE_ITEM_COMPLETE_UN = 0
        const val TYPE_ITEM_COMPLETE = 1
    }

//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
//        return when (viewType) {
//            TYPE_ITEM_COMPLETE_UN -> getItemViewHolder(parent)
//            TYPE_ITEM_COMPLETE -> getCompleteItemViewHolder(parent)
//            else -> getItemViewHolder(parent)
//        }
//    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val taskListInfo = mList!![position]
        val taskListViewHolder = holder as TaskListViewHolder
        taskListViewHolder.bind(taskListInfo, position)
    }


    inner class TaskListViewHolder(private val binding: ItemTaskListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            setOnItemClickListener { view, position ->

            }
        }

        fun bind(task: Task, position: Int) {
            binding.taskList = task
            binding.tvEndTime.text = task.taskCreateTime.toDateStr()
        }
    }

    override fun getItemViewHolder(parent: ViewGroup?): RecyclerView.ViewHolder {
        return TaskListViewHolder(
            ItemTaskListBinding.bind(
                getView(
                    parent!!,
                    R.layout.item_task_list
                )
            )
        )
    }


    override fun getItemViewType(position: Int): Int {
        return mList!![position].taskStatus
    }

    private lateinit var onItemCheckStatusListener: OnItemCheckStatusListener

    fun setOnItemCheckStatusListener(onItemCheckStatusListener: OnItemCheckStatusListener) {
        this.onItemCheckStatusListener = onItemCheckStatusListener
    }

    interface OnItemCheckStatusListener {
        fun onCheckItem(view: View, status: Int, position: Int)
    }

    override fun getItemId(position: Int): Long {
        return list!![position].taskCreateTime
    }
}