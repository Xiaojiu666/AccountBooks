package com.gx.task

import android.content.Context
import com.gx.task.repository.AppContainer
import com.gx.base.application.BaseApplication

//@HiltAndroidApp
class TaskApplication : BaseApplication() {

    val appContainer = AppContainer()


    override fun initBasePlugin() {
        super.initBasePlugin()
        val appContainer = AppContainer()
    }
}