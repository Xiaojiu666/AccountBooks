package com.gx.base.config

import android.content.Context
import com.gx.utils.apk.AppInfoUtil

object AppConfig {

    const val ACTIVITY_LOGIN = "/Test/Login"

    var ROOT_PATH = ""

    fun initConfig(context: Context) {
        ROOT_PATH = context.getExternalFilesDir(AppInfoUtil.getAppName(context))!!.path
    }

}