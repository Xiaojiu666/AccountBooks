package com.sn.libaray.log.filter

import java.time.LocalTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class LoggerLevelFilter(
    var level: LoggerLevel,
    var match: FilterResult,
    var misMatch: FilterResult,
) : AbstractFilter(match, misMatch) {

    override fun filter(methodLevel: LoggerLevel?): FilterResult? {
        if (methodLevel!!.intLevel >= level!!.intLevel) {
            return FilterResult.NEUTRAL
        }
        return super.filter(level)
    }


    companion object {

        fun createFilter(
            level: LoggerLevel?,
            match: FilterResult?,
            mismatch: FilterResult?
        ): LoggerLevelFilter {
            val onMatch: FilterResult = match ?: FilterResult.NEUTRAL
            val onMismatch: FilterResult = mismatch ?: FilterResult.DENY
            val onLevel: LoggerLevel = level ?: LoggerLevel.ALL
            return LoggerLevelFilter(onLevel, onMatch, onMismatch)
        }
    }


}