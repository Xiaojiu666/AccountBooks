package com.gx.base.config

import android.content.Context
import android.util.Log
import com.gx.base.BuildConfig
import com.gx.utils.apk.AppInfoUtil
import java.io.File

object AppFileConfig {

    var LOG_PATH = AppConfig.ROOT_PATH + File.separator + "Log"

    var LOG_XLOG_PATH = LOG_PATH + File.separator + "XLog"
    var LOG_XLOG_CACHE_PATH = LOG_PATH + File.separator + "XLogCache"

}