package com.sn.libaray.log

import android.util.Log
import com.sn.libaray.log.filter.FilterResult
import com.sn.libaray.log.filter.LoggerLevel
import com.sn.libaray.log.service.AbsLogService
import com.sn.libaray.log.service.LoggerService
import java.lang.reflect.Method

object LogUtils {

    init {
        System.loadLibrary("stlport_shared")
        System.loadLibrary("marsxlog")
    }

    var logUtilConfig: LogUtilConfig? = null

    fun initConfig(logUtilConfig: LogUtilConfig) {
        this.logUtilConfig = logUtilConfig
    }

    fun v(message: Any) {
        logUtilConfig!!.logService?.verbose(message)
    }

    fun v(tag: String, message: Any) {
        logUtilConfig!!.logService?.verbose(tag, message)
    }


    fun e(tag: String, message: Any) {
        logUtilConfig!!.logService?.error(tag, message)
    }

    fun e(message: Any) {
        logUtilConfig!!.logService?.error(message)
    }

    fun d(tag: String, message: Any) {
        logUtilConfig!!.logService?.debug(tag, message)
    }

    fun d(message: Any) {
        logUtilConfig!!.logService?.debug(message)
    }

}