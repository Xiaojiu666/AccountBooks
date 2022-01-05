package com.gx.task.ui.adapter

import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.gx.base.base.BaseRecyclerViewAdapter
import com.gx.module_task.R
import com.gx.data.task.Plan
import com.gx.module_task.databinding.ItemPlanListBinding
import com.gx.task.ui.fragment.TaskHomeFragmentDirections


class RvPlanListAdapter(mList: MutableList<Plan>?) :
    BaseRecyclerViewAdapter<Plan>(mList) {

    private var dataBinding: ItemPlanListBinding? = null

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val taskListInfo = mList!![position]
        dataBinding!!.planList = taskListInfo
    }

    inner class TaskListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//        init {
////            setOnItemClickListener { view, position ->
////                navigateToPlant(position, view)
////            }
//        }
    }

    private fun navigateToPlant(plantId: Int, view: View) {
        val direction = TaskHomeFragmentDirections.actionTaskHomeDetail(0)
        view.findNavController().navigate(direction)
    }

    override fun getItemViewHolder(parent: ViewGroup?): RecyclerView.ViewHolder {
        dataBinding = ItemPlanListBinding.bind(getView(parent!!, R.layout.item_plan_list))
        return TaskListViewHolder(dataBinding!!.root)
    }
}