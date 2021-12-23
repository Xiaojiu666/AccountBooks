package com.gx.room.task

import androidx.room.Dao
import com.gx.data.task.Task

@Dao
interface TaskDao : BaseRoomDao<Task>{
}