package com.gx.task

import com.gx.data.task.Task
import java.util.ArrayList


val ITEMS: MutableList<Task> = ArrayList()

fun getTaskData(): MutableList<Task> {
    for (i in 1..20) {
        var name = "第 $i 个任务"
        val taskListInfo = Task(name)
        ITEMS.add(taskListInfo)
    }
    return ITEMS
}