package com.sn.libaray.log.service

import android.text.Layout
import android.util.Log
import com.sn.libaray.log.FilterMapping
import com.sn.libaray.log.LogUtils
import com.sn.libaray.log.TAG
import com.sn.libaray.log.filter.AbstractFilter
import com.sn.libaray.log.filter.FilterResult
import com.sn.libaray.log.filter.LogFilter
import com.sn.libaray.log.filter.LoggerLevel
import com.sn.libaray.log.layout.DefaultLogLayout
import com.sn.libaray.log.layout.LogLayout
import com.sn.libaray.log.output.AbsOutput
import java.lang.reflect.Method

open class AbsLogService : LoggerService {

    var filter: LogFilter? = AbstractFilter()

    var tagLayout: LogLayout? = DefaultLogLayout()

    var messageLayout: LogLayout? = DefaultLogLayout()

    var output: AbsOutput? = null

    var consoleLogOpen: Boolean = true

    open fun initService() {
        Log.d(
            TAG,
            "initLog " +
                    "consoleLogOpen: {$consoleLogOpen} " +
                    ", logPath:{${output!!.fileFile}}" +
                    " , cachePath:{${output!!.fileFile}}"
        )
    }


    override fun verbose(message: Any) {
        verbose(TAG, message)
    }

    override fun verbose(tag: String, message: Any) {
        filterMsg(tag, message, LoggerLevel.VERBOSE)
    }

    private fun filterMsg(tag: String, message: Any, loggerLevel: LoggerLevel) {
        filter?.setLevel(loggerLevel)
        if (filter?.filter() == FilterResult.DENY) {
            return
        }
        if (consoleLogOpen) {
            when (loggerLevel) {
                LoggerLevel.DEBUG ->{
                    Log.v(tagLayout!!.format(tag), messageLayout!!.format(message))
                }
                LoggerLevel.VERBOSE -> {
                    Log.v(tagLayout!!.format(tag), messageLayout!!.format(message))
                }
                LoggerLevel.ERROR -> {
                    Log.e(tagLayout!!.format(tag), messageLayout!!.format(message))
                }

            }

        }
    }

    override fun verbose(tag: String, message: Any, vararg params: String) {
        verbose(tag, String.format(message.toString(), *params))
    }

    override fun debug(message: Any) {
        Log.d(TAG, "debug $message")
    }

    override fun debug(tag: String, message: Any) {
        filterMsg(tag, message, LoggerLevel.DEBUG)
    }

    override fun info(message: Any) {
        Log.i(TAG, "info $message")
    }

    override fun warn(message: Any) {
        Log.w(TAG, "warn $message")
    }

    override fun error(message: Any) {
        error(TAG, message)
    }

    override fun error(tag: String, message: Any) {
        filterMsg(tag, message, LoggerLevel.ERROR)
    }

}