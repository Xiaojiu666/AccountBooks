package com.sn.libaray.log.filter

open class AbstractFilter(
   private var match: FilterResult = FilterResult.NEUTRAL,
   private var misMatch: FilterResult = FilterResult.DENY
) : LogFilter {

    var destLevel: LoggerLevel = LoggerLevel.ALL

    override fun getOnMatch(): FilterResult {
        return match
    }

    override fun getOnMismatch(): FilterResult {
        return misMatch
    }


    override fun filter(): FilterResult? {
        return FilterResult.NEUTRAL
    }

    override fun setLevel(loggerLevel: LoggerLevel) {
        destLevel = loggerLevel
    }


}