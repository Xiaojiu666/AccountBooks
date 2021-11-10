package com.gx.di

import androidx.fragment.app.DialogFragment
import com.gx.ui.dialog.LoadingDialog
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UiModule {

    @Provides
    @Singleton
    fun provideLoadDialog(): DialogFragment {
        return LoadingDialog()
    }
}