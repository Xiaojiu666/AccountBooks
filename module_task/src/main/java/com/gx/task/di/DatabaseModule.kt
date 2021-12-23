package com.gx.task.di

import android.content.Context
import com.gx.room.NoteDatabase
import com.gx.room.task.PlanDao
import com.gx.room.task.TaskDao
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
    fun provideTaskDatabase(@ApplicationContext context: Context):NoteDatabase{
        return NoteDatabase.getInstance(context)!!
    }
    @Provides
    fun providePlanDao(taskDatabase: NoteDatabase): PlanDao {
        return taskDatabase.planDao()!!
    }

    @Provides
    fun provideSubTaskDao(taskDatabase: NoteDatabase): TaskDao {
        return taskDatabase.taskDao()!!
    }

}