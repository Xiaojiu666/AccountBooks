package com.sn.libaray.log.filter

interface LogFilter {

    fun getOnMatch(): FilterResult

    fun getOnMismatch(): FilterResult

    fun filter(): FilterResult?

    fun setLevel(loggerLevel: LoggerLevel)


}