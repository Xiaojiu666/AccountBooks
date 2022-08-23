package com.sn.libaray.log

import android.util.Log
import com.sn.libaray.log.filter.LoggerLevel

class LoggerServiceImpl : LoggerService {

    override fun verbose(message: CharSequence) {
        Log.v(TAG, "verbose $message")
    }

    override fun verbose(message: CharSequence, vararg params: String) {

    }

    override fun verbose(message: CharSequence, vararg params: String, level: LoggerLevel) {

    }

    override fun debug(message: CharSequence) {
        Log.d(TAG, "debug $message")
    }

    override fun info(message: CharSequence) {
        Log.i(TAG, "info $message")
    }

    override fun warn(message: CharSequence) {
        Log.w(TAG, "warn $message")
    }

    override fun error(message: CharSequence) {
        Log.e(TAG, "error $message")
    }
}