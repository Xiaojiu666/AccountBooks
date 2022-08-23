package com.sn.libaray.log.filter

interface LogFilter {

    fun getOnMatch(): FilterResult

    fun getOnMismatch(): FilterResult

    fun filter(level: LoggerLevel?, p0: Any?): FilterResult?

    fun filter(level: LoggerLevel?): FilterResult?

    fun filter(): FilterResult?


}