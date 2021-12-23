package com.gx.data.task

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gx.room.RoomConfig
import com.gx.room.RoomConfig.TASK_TABLE_NAME


@Entity(tableName = TASK_TABLE_NAME)
data class Task(var parentPlanId: Long) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = RoomConfig.TASK_TABLE_PRIMARY_KEY)
    var taskId  = 0

    var taskName : String? = null

    var taskListStatus = 0

    var taskCreateTime: Long = System.currentTimeMillis()

    var taskEndTime: Long = 0L

    var taskNotificationTime: Long = 0L

    var taskDescription: String? = null

}