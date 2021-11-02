package com.gx.task.repository

import com.gx.data.task.TaskDetailInfo
import com.gx.data.task.Task

class TaskDataLocalSource : TaskDataSource {
    override fun getTaskList() = ArrayList<Task>()

    override fun getTaskDetails() = TaskDetailInfo(1)
}