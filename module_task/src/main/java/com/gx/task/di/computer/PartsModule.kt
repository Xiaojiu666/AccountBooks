package com.gx.task.di.computer

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.gx.module_task.R
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PartsModule() {

//    @Provides
//    fun provideElectric() = Electric()

    @Provides
    fun provideMainBoard(): MainBoard {
        return MainBoard.create()!!
    }

    @Provides
    fun provideCPU(): CPU {
        return CPU.create()!!
    }
}