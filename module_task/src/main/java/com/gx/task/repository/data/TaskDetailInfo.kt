package com.gx.task.repository.data

data class TaskDetailInfo(var taskId:Int) {
    var taskTitle: String? = null

    var taskContent :String?=null

    override fun toString(): String {
        return "taskTitle $taskTitle + taskContent$ $taskContent + taskId $taskId "
    }

}