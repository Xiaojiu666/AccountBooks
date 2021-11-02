package com.gx.data.task

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gx.room.RoomConfig.TASK_TABLE_NAME
import com.gx.room.RoomConfig.TASK_TABLE_PRIMARY_KEY


@Entity(tableName = TASK_TABLE_NAME)
data class Task(var title: String) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = TASK_TABLE_PRIMARY_KEY)
    var taskId: Long = 0

    var taskStartTime: Long? = 0L

    var taskEndTime: Long? = 0L
    var taskListTotalSize: Int = 0
    var taskListCompleteSize: Int = 0

    // 0 进行中 1 已完成 2 延期
    var taskListStatus: Int = 0
    var taskDescription: String? = null

    override fun toString(): String {
        return "Task(taskId=$taskId, " +
                "taskStartTime=$taskStartTime, " +
                "taskEndTime=$taskEndTime, " +
                "taskListTotalSize=$taskListTotalSize, " +
                "taskListCompleteSize=$taskListCompleteSize, " +
                "taskListStatus=$taskListStatus, " +
                "taskDescription=$taskDescription)"
    }


}