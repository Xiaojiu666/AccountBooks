package com.gx.task.database

import androidx.room.*
import com.gx.room.task.BaseRoomDao
import com.gx.task.repository.data.Plan
import kotlinx.coroutines.flow.Flow

@Dao
interface PlanDao : BaseRoomDao<Plan> {

    @Query("SELECT * FROM `plan` ")
    fun getAllPlanData(): Flow<MutableList<com.gx.task.repository.data.Plan>>

    @Query("SELECT title FROM  `plan`")
    fun getPlanTitles(): Flow<MutableList<String>>

}