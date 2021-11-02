package com.gx.data.task

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gx.room.RoomConfig
import com.gx.room.RoomConfig.TASK_TABLE_NAME


@Entity(tableName = TASK_TABLE_NAME)
data class SubTask(var subTitle: String) {


    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = RoomConfig.TASK_TABLE_PRIMARY_KEY)
    var subTaskId = 0

    var subTaskListStatus: TaskStatus = TaskStatus.IN_PROGRESS

    var parentId = 0
    var parentName: String? = null



    var subTaskListTotalSize: Int = 0
    var subTaskListCompleteSize: Int = 0

    var subTaskDescription: String? = null

}