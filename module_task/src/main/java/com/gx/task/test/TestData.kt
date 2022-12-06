package com.gx.task

import com.gx.task.repository.data.Plan
import com.gx.task.repository.data.Task
import java.util.ArrayList


val ITEMS: MutableList<Plan> = ArrayList()
val Task: MutableList<Task> = ArrayList()

fun getPlanData(): MutableList<Plan> {
    for (i in 1..20) {
        var name = "第 $i 个任务"
        val taskListInfo = Plan(name)
        ITEMS.add(taskListInfo)
    }
    return ITEMS
}


fun getTaskData():MutableList<Task>{
    for (i in 1..20) {
        var name = "第 $ 个任务"
        val taskListInfo = Task(2)
        taskListInfo.taskName = name
        taskListInfo.taskCreateTime =System.currentTimeMillis()
        Task.add(taskListInfo)
    }
    return Task
}