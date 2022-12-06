package com.gx.task.vm

import androidx.lifecycle.*
import com.gx.task.repository.data.Task
import com.gx.task.repository.data.Plan
import com.gx.task.repository.TaskRepository
import com.sn.libaray.log.LogUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    var taskRepository: TaskRepository
) : ViewModel() {

    val TAG = "TaskViewModel"
    val mPlans: LiveData<MutableList<Plan>> = taskRepository.getPlanList().asLiveData()
    var mPlanInfo: Plan = Plan("")


    lateinit var mTasks: LiveData<MutableList<Task>>

    lateinit var mUnTasks: LiveData<MutableList<Task>>

    lateinit var allTask: LiveData<MutableList<Task>>

    var currentSelectorPlan: Plan? = null

    var test: LiveData<String> = liveData { }


    override fun onCleared() {
        super.onCleared()
//        LogUtil.e("TaskViewModel", "onCleared")
    }


    fun getTaskList4PlanId(paintId: Long) {
        viewModelScope.launch {
//            mTasks = taskRepository.getTaskList4PlanId(paintId, 1).asLiveData()
//            mUnTasks = taskRepository.getTaskList4PlanId(paintId, 0).asLiveData()
            allTask = taskRepository.getTaskList4PlanId(paintId).asLiveData()
        }
    }


    fun insertPlan(plan: Plan) {
        viewModelScope.launch {
            taskRepository.insertPlan(plan)
        }
    }

    fun insertTask(task: Task) {
        viewModelScope.launch {
            var result = taskRepository.insertTask(task)
            LogUtils.e(TAG, "result $result")
        }
    }


    fun upDateTaskList(taskList: MutableList<Task>) {
//        LogUtil.d(TAG, "upDateTaskList ${taskList.toString()}")
//        LogUtil.d(TAG, "upDateTaskList ${taskRepository.toString()}")
//        LogUtil.d(TAG, "upDateTaskList ${viewModelScope.toString()}")
        GlobalScope.launch(Dispatchers.IO) {
            taskRepository.upgradeTasks(taskList)
        }
    }

    fun upDateTask(task: Task) {
//        LogUtil.d(TAG, "upgradeTask ${task.toString()}")
//        LogUtil.d(TAG, "upgradeTask ${taskRepository.toString()}")
//        LogUtil.d(TAG, "upgradeTask ${viewModelScope.toString()}")
        GlobalScope.launch(Dispatchers.IO) {
            taskRepository.upgradeTask(task)
        }
    }
}


