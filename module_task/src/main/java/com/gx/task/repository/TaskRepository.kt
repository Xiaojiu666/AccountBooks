package com.gx.task.repository

import com.gx.data.task.Task
import com.gx.room.task.TaskDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskRepository @Inject constructor(
    var localDataSource: TaskLocalDataSource,
    var remoteDataSource: TaskRemoteDataSource
) {

    fun getTaskList() = localDataSource.getTaskList()

    fun insertTask(task: Task) = localDataSource.insertTask(task)

    fun insertTasks(task: MutableList<Task>) = localDataSource.insertTasks(task)


    class TaskLocalDataSource @Inject constructor(private var taskDao: TaskDao) {
        fun getTaskList() = taskDao.getAllTaskData()

        fun insertTask(task: Task) = taskDao.insert(task)

        fun insertTasks(task: MutableList<Task>) =taskDao.insertAll(task)

    }

    class TaskRemoteDataSource@Inject constructor(){

    }

}