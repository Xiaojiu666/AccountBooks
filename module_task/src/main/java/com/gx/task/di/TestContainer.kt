package com.gx.task.di

import com.gx.task.TaskActivity
import dagger.Component

@Component
interface TestContainer {
//    fun repository(): TestRepository

    fun inject(taskActivity: TaskActivity)
}