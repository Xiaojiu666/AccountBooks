package com.sn.libaray.log

import com.sn.libaray.log.filter.LoggerLevel

interface LoggerService {

    @FilterMapping(level = LoggerLevel.VERBOSE)
    fun verbose(message: CharSequence)

    @FilterMapping(level = LoggerLevel.VERBOSE)
    fun verbose(message: CharSequence, vararg params: String)

    @FilterMapping(level = LoggerLevel.VERBOSE)
    fun verbose(message: CharSequence, vararg params: String, level: LoggerLevel)

    @FilterMapping(level = LoggerLevel.DEBUG)
    fun debug(message: CharSequence)

    @FilterMapping(level = LoggerLevel.INFO)
    fun info(message: CharSequence)

    @FilterMapping(level = LoggerLevel.WARN)
    fun warn(message: CharSequence)

    @FilterMapping(level = LoggerLevel.ERROR)
    fun error(message: CharSequence)

}