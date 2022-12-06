package com.gx.task.di.base

//@Singleton
//@Component(modules = [BaseModule::class])
interface BaseContainer {
    fun inject(baseActivity: BaseActivity)
}