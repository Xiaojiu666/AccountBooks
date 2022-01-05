package com.gx.task.vm

import androidx.lifecycle.*
import com.gx.data.task.Task
import com.gx.data.task.Plan
import com.gx.task.repository.TaskRepository
import com.gx.utils.log.LogUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    var taskRepository: TaskRepository
) : ViewModel() {

    val TAG = "TaskViewModel"
    val mPlans: LiveData<MutableList<Plan>> = taskRepository.getPlanList().asLiveData()


//    val mPlans: LiveData<MutableList<Plan>> = taskRepository.getTaskList4PlanId().asLiveData()

    lateinit var mTasks: LiveData<MutableList<Task>>

//    val mTask: MutableLiveData<Task> by lazy {
//        MutableLiveData()
//    }
//
//    val mPlanTitles: LiveData<MutableList<String>> =
//        taskRepository.getPlanTitleList().asLiveData()

    lateinit var currentSelectorPlan: Plan


    override fun onCleared() {
        super.onCleared()
        LogUtil.e("TaskViewModel", "onCleared")
    }

    fun getTaskList4PlanId(paintId: Long) {
        mTasks = taskRepository.getTaskList4PlanId(paintId).asLiveData()
    }


    fun insertPlan(plan: Plan) {
        viewModelScope.launch {
            taskRepository.insertPlan(plan)
        }
    }

    fun insertTask(task: Task) {
        viewModelScope.launch {
            taskRepository.insertTask(task)
        }
    }


    suspend fun upDateTaskList(taskList: MutableList<Task>) {
        LogUtil.d(TAG, "upDateTaskList ${taskList.toString()}")
        LogUtil.d(TAG, "upDateTaskList ${taskRepository.toString()}")
        LogUtil.d(TAG, "upDateTaskList ${viewModelScope.toString()}")
        taskRepository.upgradeTasks(taskList)
    }

    suspend fun upDateTask(task: Task) {
        LogUtil.d(TAG, "upgradeTask ${task.toString()}")
        LogUtil.d(TAG, "upgradeTask ${taskRepository.toString()}")
        LogUtil.d(TAG, "upgradeTask ${viewModelScope.toString()}")
        taskRepository.upgradeTask(task)
    }
}


