package com.sn.libaray.log.filter

import java.util.logging.Level.OFF

enum class LoggerLevel(var intLevel: Int) {


    /**
     * No events will be logged.
     */
    VERBOSE(0),

    /**
     * A severe error that will prevent the application from continuing.
     */
    DEBUG(1),

    /**
     * An error in the application, possibly recoverable.
     */
    INFO(2),

    /**
     * An event that might possible lead to an error.
     */
    WARN(3),

    /**
     * An event for informational purposes.
     */
    ERROR(4),


    /**
     * All events should be logged.
     */
    ALL(-1);

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