package com.gx.task.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.gx.data.task.Task
import com.gx.task.getTaskData
import com.gx.task.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    var taskRepository: TaskRepository
) : ViewModel() {

    val tasks: LiveData<MutableList<Task>> = getTasks()


    suspend fun getTasks() = withContext(Dispatchers.IO) {
        taskRepository.getTaskList().asLiveData()
    }

    fun insertTask(task: Task) = taskRepository.insertTask(task)


}