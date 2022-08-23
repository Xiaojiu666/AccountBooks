package com.sn.libaray.log

import com.sn.libaray.log.filter.LogFilter
import com.sn.libaray.log.filter.LoggerLevel
import com.sn.libaray.log.filter.LoggerLevelFilter
import com.sn.libaray.log.filter.TimeFilter


val Any.TAG: String
    get() {
        val tag = javaClass.simpleName
        return if (tag.length <= 23) tag else tag.substring(0, 23)
    }

var filter: LogFilter =
    TimeFilter.createFilter("00:00:00", "24:00:00", "America/Los_Angeles", null, null);
var levelFilter: LogFilter =
    LoggerLevelFilter.createFilter(null, null, null);

val logger: LoggerService
    get() {
        return Logger.Builder(LoggerServiceImpl()).setLoggerFilter(levelFilter).build()
    }

