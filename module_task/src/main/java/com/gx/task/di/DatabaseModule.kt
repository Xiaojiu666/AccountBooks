package com.gx.task.di

import android.content.Context
import com.gx.task.database.PlanDao
import com.gx.task.database.TaskDao
import com.gx.task.database.TaskDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideTaskDatabase(@ApplicationContext context: Context):TaskDatabase{
        return TaskDatabase.getInstance(context)!!
    }
    @Provides
    fun providePlanDao(taskDatabase: TaskDatabase): PlanDao {
        return taskDatabase.planDao()!!
    }

    @Provides
    fun provideSubTaskDao(taskDatabase: TaskDatabase): TaskDao {
        return taskDatabase.taskDao()!!
    }

}