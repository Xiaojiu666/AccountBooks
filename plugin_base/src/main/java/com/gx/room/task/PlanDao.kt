package com.gx.room.task

import androidx.room.*
import com.gx.data.task.Plan
import kotlinx.coroutines.flow.Flow

@Dao
interface PlanDao : BaseRoomDao<Plan> {

    @Query("SELECT * FROM `plan` ")
    fun getAllPlanData(): Flow<MutableList<Plan>>


    @Query("SELECT title FROM  `plan`")
    fun getPlanTitles(): Flow<MutableList<String>>

}