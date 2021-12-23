package com.gx.task.repository

import com.gx.data.task.Task
import com.gx.data.task.Plan
import com.gx.room.task.PlanDao
import com.gx.room.task.TaskDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskRepository @Inject constructor(
    var localDataSource: TaskLocalDataSource,
    var remoteDataSource: TaskRemoteDataSource,
) {

    fun getPlanList() = localDataSource.getPlanList()

    fun getPlanTitleList() = localDataSource.getPlanTitleList()


    suspend fun insertPlan(plan: Plan) = localDataSource.insertPlan(plan)

    suspend fun insertTask(task: Task) = localDataSource.insertTask(task)

    fun insertTasks(plan: MutableList<Plan>) = localDataSource.insertTasks(plan)


    class TaskLocalDataSource @Inject constructor(
        private var taskDao: TaskDao,
        private var planDao: PlanDao,
    ) {

        fun getPlanList() = planDao.getAllPlanData()

        fun getPlanTitleList() = planDao.getPlanTitles()

        suspend fun insertPlan(plan: Plan) = planDao.insert(plan)

        suspend fun insertTask(task: Task) = taskDao.insert(task)

        fun insertTasks(plan: MutableList<Plan>) = planDao.insertAll(plan)

    }

    class TaskRemoteDataSource @Inject constructor() {

    }

}