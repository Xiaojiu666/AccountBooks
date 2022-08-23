package com.sn.libaray.log.filter

import com.sn.libaray.log.FilterMapping
import java.util.*

enum class LoggerLevel(var intLevel: Int) {

    /**
     * No events will be logged.
     */
    VERBOSE(100),

    /**
     * A severe error that will prevent the application from continuing.
     */
    DEBUG(200),

    /**
     * An error in the application, possibly recoverable.
     */
    INFO(300),

    /**
     * An event that might possible lead to an error.
     */
    WARN(400),

    /**
     * An event for informational purposes.
     */
    ERROR(500),


    /**
     * All events should be logged.
     */
    ALL(0);

//    private val LEVELSET: EnumSet<LoggerLevel> = EnumSet.allOf(LoggerLevel::class.java)


//    open fun getStandardLevel(intLevel: Int): LoggerLevel? {
//        var level: LoggerLevel? = LoggerLevel.OFF
//        for (lvl in LEVELSET) {
//            if (lvl.intLevel > intLevel) {
//                break
//            }
//            level = lvl
//        }
//        return level
//    }
}