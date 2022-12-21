package com.gx.task.ui.fragment.task

import android.content.Intent
import android.text.TextUtils
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.gx.base.base.vb.BaseVBFragment
import com.gx.module_task.R
import com.gx.module_task.databinding.FragmentTaskHomeBinding
import com.gx.task.repository.data.Plan
import com.gx.task.ui.activities.TaskListActivity
import com.gx.task.ui.activities.TaskNewActivity
import com.gx.task.ui.adapter.RvPlanListAdapter
import com.gx.task.vm.TaskViewModel
import com.sn.libaray.log.LogUtils
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
        val bottomSheetDialog = BottomSheetDialog(requireContext())
        bottomSheetDialog.setContentView(R.layout.layout_plan_add)
        val bottomSheetInternal = bottomSheetDialog.findViewById<View>(R.id.design_bottom_sheet)
        val dialogCancel = bottomSheetInternal!!.findViewById<TextView>(R.id.dialog_plan_cancel)
        val dialogCreate = bottomSheetInternal.findViewById<TextView>(R.id.dialog_plan_create)
        val dialogPlanEdText = bottomSheetInternal.findViewById<TextView>(R.id.dialog_plan_ed_text)
        dialogCancel.setOnClickListener {
            bottomSheetDialog.cancel()
        }

        dialogCreate.setOnClickListener {
            var planName = dialogPlanEdText.text
            if (TextUtils.isEmpty(planName)) {
                Toast.makeText(context, "请输入计划名称", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val plan = Plan(planName.toString())
            plan.planListCompleteSize = 1
            plan.planEndTime = System.currentTimeMillis()
            plan.planStartTime = System.currentTimeMillis()
            viewModel.insertPlan(plan)
            bottomSheetDialog.cancel()
        }

        val behavior = BottomSheetBehavior.from(bottomSheetInternal)
        bottomSheetInternal.layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
        behavior.peekHeight = 1800
        planTvCreate.setOnClickListener {
            bottomSheetDialog.show()
        }
        with(taskRecyclerView) {
            layoutManager = LinearLayoutManager(context)
            adapter = rvTaskListAdapter
        }
    }


    override fun initView() {
        super.initView()
        viewModel.mPlans.observe(this) {
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


    private fun startNewTaskActivity() {
        val intent = Intent(activity, TaskNewActivity::class.java)
        startActivity(intent)
    }

    private fun startTaskListActivity(plan: Plan) {
        val intent = Intent(activity, TaskListActivity::class.java)
        intent.putExtra("planInfo", plan)
        LogUtils.d(TAG, "planId  $plan")
        startActivity(intent)
    }


}