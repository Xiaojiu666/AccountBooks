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

    val mPlans: LiveData<MutableList<Plan>> = taskRepository.getPlanList().asLiveData()

    val mTask: MutableLiveData<Task> by lazy {
        MutableLiveData()
    }

    val mPlanTitles: LiveData<MutableList<String>> =
        taskRepository.getPlanTitleList().asLiveData()

    lateinit var currentSelectorPlan: Plan


    override fun onCleared() {
        super.onCleared()
        LogUtil.e("TaskViewModel", "onCleared")
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
}


