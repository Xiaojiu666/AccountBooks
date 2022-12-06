package com.gx.task.repository

import com.gx.data.task.TaskDetailInfo
import com.gx.task.repository.data.Plan

class TaskDataLocalSource : TaskDataSource {
    override fun getTaskList() = ArrayList<Plan>()

    override fun getTaskDetails() = TaskDetailInfo(1)
}