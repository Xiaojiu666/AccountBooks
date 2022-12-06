package com.gx.task.repository.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gx.room.RoomConfig
import com.gx.room.RoomConfig.TASK_TABLE_NAME
import com.gx.utils.date.toDateStr


@Entity(tableName = TASK_TABLE_NAME)
data class Task(var parentPlanId: Long) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = RoomConfig.TASK_TABLE_PRIMARY_KEY)
    var taskId = 0

    var taskName: String? = null

    var taskListStatus = 0

    // 0 - 未完成
    // 1 - 已完成
    // 2 - 延期
    var taskStatus: Int = 0

    var taskCreateTime: Long = System.currentTimeMillis()

    var taskEndTime: Long = 0L

//    fun getTaskEndTime(): String {
//        return taskEndTime.toDateStr()
//    }

    var taskNotificationTime: Long = 0L

    var taskDescription: String? = null
    override fun toString(): String {
        return "Task(parentPlanId=$parentPlanId, " +
                "taskId=$taskId, " +
                "taskStatus=$taskStatus, " +
                "taskName=$taskName," +
                "taskListStatus=$taskListStatus, " +
                "taskCreateTime=$taskCreateTime," +
                "taskEndTime=$taskEndTime, " +
                "taskNotificationTime=$taskNotificationTime," +
                "taskDescription=$taskDescription)"
    }


}