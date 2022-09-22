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

    val TAG = "TaskRepository"

    fun getPlanList() = localDataSource.getPlanList()

    fun getPlanTitleList() = localDataSource.getPlanTitleList()

    fun getTaskList4PlanId(paintId: Long, taskStatus: Int) = localDataSource.getTaskList4PlanId(paintId, taskStatus)

    fun getTaskList4PlanId(paintId: Long) = localDataSource.getTaskList4PlanId(paintId)

    suspend fun insertPlan(plan: Plan) = localDataSource.insertPlan(plan)

    suspend fun insertTask(task: Task) = localDataSource.insertTask(task)


    fun insertPlans(plan: MutableList<Plan>) = localDataSource.insertPlans(plan)

    fun insertTasks(task: MutableList<Task>) = localDataSource.insertTasks(task)



    fun upgradeTasks(taskList: MutableList<Task>) {
//        LogUtil.d(TAG, "upgradeTasks ${taskList.toString()}")
        localDataSource.upgradeTasks(taskList)
    }


    suspend fun upgradeTask(task: Task) {
//        LogUtil.d(TAG, "upgradeTask ${task.toString()}")
        localDataSource.upgradeTask(task)
    }

    class TaskLocalDataSource @Inject constructor(
        private var taskDao: TaskDao,
        private var planDao: PlanDao,
    ) {

        fun getPlanList() = planDao.getAllPlanData()

        fun getPlanTitleList() = planDao.getPlanTitles()

        fun getTaskList4PlanId(paintId: Long, taskStatus: Int) =
            taskDao.getTaskList4PlanId(paintId, taskStatus)

        fun getTaskList4PlanId(paintId: Long) =
            taskDao.getTaskList4PlanId(paintId)

        fun upgradeTasks(taskList: MutableList<Task>) = taskDao.upgradeTasks(taskList)

        suspend fun upgradeTask(task: Task) = taskDao.upgradeTask(task)

        suspend fun insertPlan(plan: Plan) = planDao.insert(plan)

        suspend fun insertTask(task: Task) = taskDao.insert(task)

        fun insertPlans(plan: MutableList<Plan>) = planDao.insertAll(plan)

        fun insertTasks(task: MutableList<Task>) = taskDao.insertAll(task)

    }

    class TaskRemoteDataSource @Inject constructor() {

    }

}