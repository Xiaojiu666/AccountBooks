package com.sn.libaray.log

import com.sn.libaray.log.filter.LogFilter
import com.sn.libaray.log.filter.LoggerLevelFilter
import com.sn.libaray.log.filter.TimeFilter
import com.sn.libaray.log.service.LogLogService
import com.sn.libaray.log.service.LoggerService
import com.sn.libaray.log.service.XLogService


val Any.TAG: String
    get() {
        val tag = javaClass.simpleName
        return if (tag.length <= 23) tag else tag.substring(0, 23)
    }

var filter: LogFilter =
    TimeFilter.createFilter("00:00:00", "24:00:00", "America/Los_Angeles", null, null);
var levelFilter: LogFilter =
    LoggerLevelFilter.createFilter(null);

val logger: LoggerService
    get() {
        val logService = LogLogService()
        val xlogService = XLogService()
        return Logger.Builder(xlogService).setLoggerFilter(levelFilter).build()
    }

