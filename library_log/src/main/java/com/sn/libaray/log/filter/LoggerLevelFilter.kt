package com.sn.libaray.log.filter

class LoggerLevelFilter(
    var loggerLevel: LoggerLevel?,
    match: FilterResult,
    misMatch: FilterResult,
) : AbstractFilter(match, misMatch) {

    fun filter(methodLevel: LoggerLevel?): FilterResult? {
        if (methodLevel!!.intLevel >= loggerLevel!!.intLevel) {
            return FilterResult.NEUTRAL
        }
        return FilterResult.DENY
    }

    override fun filter(): FilterResult? {
        return filter(destLevel)
    }

    companion object {

        fun createFilter(
            level: LoggerLevel?,
            match: FilterResult = FilterResult.NEUTRAL,
            mismatch: FilterResult = FilterResult.DENY
        ): LoggerLevelFilter {
            val onLevel: LoggerLevel = level ?: LoggerLevel.ALL
            return LoggerLevelFilter(onLevel, match, mismatch)
        }
    }


}