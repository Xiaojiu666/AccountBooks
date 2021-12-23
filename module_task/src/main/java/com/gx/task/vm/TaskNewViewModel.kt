package com.gx.task.vm

import androidx.lifecycle.ViewModel
import com.gx.data.task.Task
import com.gx.task.repository.TaskRepository
import javax.inject.Inject

class TaskNewViewModel @Inject constructor(
    var taskRepository: TaskRepository
) : ViewModel() {

    fun crateTask(task: Task){

    }
}