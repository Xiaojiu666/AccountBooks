package com.gx.task

import com.gx.data.task.Plan
import java.util.ArrayList


val ITEMS: MutableList<Plan> = ArrayList()

fun getTaskData(): MutableList<Plan> {
    for (i in 1..20) {
        var name = "第 $i 个任务"
        val taskListInfo = Plan(name)
        ITEMS.add(taskListInfo)
    }
    return ITEMS
}