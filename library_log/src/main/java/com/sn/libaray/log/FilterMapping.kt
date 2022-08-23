package com.sn.libaray.log

import com.sn.libaray.log.filter.LoggerLevel
import com.sn.libaray.log.filter.LoggerLevelFilter
import java.lang.annotation.Documented

@Documented
@kotlin.annotation.Target(AnnotationTarget.FUNCTION)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class FilterMapping(val level: LoggerLevel = LoggerLevel.DEBUG) {

}
