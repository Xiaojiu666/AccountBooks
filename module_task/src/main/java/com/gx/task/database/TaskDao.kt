package com.gx.task.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import com.gx.task.repository.data.Task
import com.gx.room.task.BaseRoomDao
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao : BaseRoomDao<Task> {

    @Query("SELECT * FROM task Where parentPlanId =:planId AND taskStatus =:taskStatus ORDER BY taskCreateTime ")
    fun getTaskList4PlanId(planId: Long, taskStatus: Int): Flow<MutableList<Task>>

    @Query("SELECT * FROM task Where parentPlanId =:planId ORDER BY taskCreateTime DESC")
    fun getTaskList4PlanId(planId: Long): Flow<MutableList<Task>>

    @Update
    fun upgradeTasks(taskList: MutableList<Task>)

    @Update
    fun upgradeTask(task: Task)
}