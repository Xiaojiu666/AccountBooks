package com.gx.task.repository

import com.gx.data.task.TaskDetailInfo
import com.gx.task.repository.data.Plan

interface TaskDataSource {
    fun getTaskList() : List<Plan>

    fun getTaskDetails(): TaskDetailInfo
}