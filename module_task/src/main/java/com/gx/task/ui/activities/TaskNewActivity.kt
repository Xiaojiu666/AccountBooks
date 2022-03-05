package com.gx.task.ui.activities

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.gx.base.base.vb.BaseVBActivity
import com.gx.data.task.Task
import com.gx.data.task.Plan
import com.gx.module_task.R
import com.gx.module_task.databinding.ActivityTaskNewBinding
import com.gx.task.ui.adapter.RvTaskNameAdapter
import com.gx.task.vm.TaskViewModel
import com.gx.utils.log.LogUtil
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TaskNewActivity : BaseVBActivity<ActivityTaskNewBinding>() {

    private val viewModel: TaskViewModel by viewModels()
    private var rvTaskNameAdapter: RvTaskNameAdapter = RvTaskNameAdapter(null)
    private lateinit var editText: EditText
    private lateinit var negativeButton: AlertDialog

    override fun ActivityTaskNewBinding.initBinding() {
        initClickListener()
        initActionBar(toolbar)
        tvChoicePlan.setOnClickListener {
            initPlanDialog()
        }
        taskEtDesc.addTextChangedListener(MyTextWatcher())
    }

    inner class MyTextWatcher : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        @SuppressLint("SetTextI18n")
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            LogUtil.d(TAG, "start $start , before $before , count $count")
            mBinding.textDescSize.text = "${start + count}/140"
        }

        override fun afterTextChanged(s: Editable?) {
        }
    }

    private fun initClickListener() {
        rvTaskNameAdapter.setOnItemClickListener { view, posiion ->
            val task = viewModel.mPlans.value!![posiion]
            LogUtil.d(TAG, "task : $task")
            viewModel.currentSelectorPlan = task
            mBinding.tvChoicePlan.setTextColor(getColor(R.color.base_color_black))
            mBinding.tvChoicePlan.text = task.title
            negativeButton.cancel()
        }
    }

    private fun initPlanDialog() {
        viewModel.mPlans.observe(this) {
            LogUtil.e("name : $it   , name :Size $it!!.size")
            if (it!!.size <= 0) {
                createPlanDialog(getString(R.string.plan_tips))
            } else {
                createPlanListDialog(it)
            }
        }
    }

    var cancelListener = DialogInterface.OnClickListener { dialog, _ ->
        dialog.cancel()
    }

    var createPlanListener = DialogInterface.OnClickListener { dialog, _ ->
        LogUtil.d("$dialog")
        dialog.cancel()
        createPlanDialog(null)
    }

    var savePlanListener = DialogInterface.OnClickListener { dialog, _ ->
        dialog.cancel()
        val toString = editText.text.toString()
        savePlan(toString)
    }

    private fun createPlanDialog(message: String?) {
        LogUtil.d("createPlanDialog")
        val dialogView =
            View.inflate(baseContext, R.layout.dialog_custom_editext, null)
        editText = dialogView.findViewById<EditText>(R.id.plan_et_title)
        val negativeButton = MaterialAlertDialogBuilder(this)
            .setTitle(R.string.plan_create)
            .setView(dialogView)
            .setPositiveButton(R.string.base_confirm, savePlanListener)
            .setNegativeButton(R.string.base_cancel, cancelListener)
        if (!TextUtils.isEmpty(message)) {
            LogUtil.d("message $message ")
            negativeButton.setMessage(message)
        }
        negativeButton.show()
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.task_create, menu)
        return true
    }

    override fun initView() {
        super.initView()
    }

    private fun createPlanListDialog(mutableList: MutableList<Plan>) {
        rvTaskNameAdapter.list = mutableList
        val dialogView =
            View.inflate(baseContext, R.layout.dialog_custom_recyclerview, null)
        val recyclerView = dialogView.findViewById<RecyclerView>(R.id.dialog_recyclerView)
        with(recyclerView) {
            adapter = rvTaskNameAdapter
            layoutManager = LinearLayoutManager(context)
            adapter!!.notifyDataSetChanged()
        }
        negativeButton = MaterialAlertDialogBuilder(this)
            .setTitle(R.string.plan_choice)
            .setView(dialogView)
            .setPositiveButton(R.string.plan_create, createPlanListener)
            .setNegativeButton(R.string.base_cancel, cancelListener)
            .show()
    }

    private fun savePlan(planName: String) {
        if (TextUtils.isEmpty(planName)) {
            Toast.makeText(this, "请输入计划名称", Toast.LENGTH_SHORT).show()
            return
        }
        LogUtil.e(TAG, " savePlan ${planName}")
        val task = Plan(planName)
        viewModel.insertPlan(task)
    }

    private fun saveTask() {
        val currentSelectorPlan = viewModel.currentSelectorPlan
        val taskTitle = mBinding.taskEtTitle.text.toString()
        if (currentSelectorPlan == null) {
            Toast.makeText(this, "请选择关联计划", Toast.LENGTH_SHORT).show()
            return
        }
        if (TextUtils.isEmpty(taskTitle)) {
            Toast.makeText(this, "请输入任务名称", Toast.LENGTH_SHORT).show()
            return
        }
        val subTask = Task(currentSelectorPlan.planId)
        with(subTask) {
            taskEndTime = System.currentTimeMillis()
            taskNotificationTime = System.currentTimeMillis()
            taskName = taskTitle
            taskDescription = mBinding.taskEtDesc.text.toString()
        }
        viewModel.insertTask(subTask)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.task_save -> {
                saveTask()
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}