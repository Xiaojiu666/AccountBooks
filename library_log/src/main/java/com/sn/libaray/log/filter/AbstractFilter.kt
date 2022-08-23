package com.sn.libaray.log.filter

open class AbstractFilter(match: FilterResult, misMatch: FilterResult) : LogFilter {


    private var onMatch: FilterResult = FilterResult.NEUTRAL

    private var onMismatch: FilterResult = FilterResult.DENY

    init {
        onMatch = match
        onMismatch = misMatch

    }

    override fun getOnMatch(): FilterResult {
        return onMatch
    }

    override fun getOnMismatch(): FilterResult {
        return onMismatch
    }

    override fun filter(level: LoggerLevel?, p0: Any?): FilterResult? {
        return onMismatch
    }

    override fun filter(level: LoggerLevel?): FilterResult? {
        return onMismatch
    }

    override fun filter(): FilterResult? {
        return FilterResult.NEUTRAL
    }


}