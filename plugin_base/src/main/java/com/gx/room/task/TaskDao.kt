package com.gx.room.task

import androidx.room.*
import com.gx.data.task.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao : BaseRoomDao<Task> {

    @Query("SELECT * FROM  task ")
    fun getAllTaskData(): Flow<MutableList<Task>>

}