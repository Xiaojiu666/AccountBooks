package com.gx.task.repository

import com.gx.data.task.TaskDetailInfo
import com.gx.data.task.Task

interface TaskDataSource {
    fun getTaskList() : List<Task>

    fun getTaskDetails(): TaskDetailInfo
}