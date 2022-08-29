package com.sn.libaray.log

import android.util.Log
import com.sn.libaray.log.filter.FilterResult
import com.sn.libaray.log.filter.LogFilter
import com.sn.libaray.log.filter.LoggerLevel
import com.sn.libaray.log.filter.TimeFilter
import java.lang.reflect.Method
import java.lang.reflect.Proxy

class Logger() {


    class Builder(var loggerServiceImpl: LoggerService) {

        private var loggerFilter: LogFilter? = null

        fun setLoggerFilter(loggerFilter: LogFilter): Builder {
            this.loggerFilter = loggerFilter
            return this
        }

        fun build(): LoggerService {
            val loggerService = LoggerService::class.java
            return Proxy.newProxyInstance(
                loggerService.classLoader, arrayOf<Class<*>>(loggerService)
            ) { proxy, method, args ->
                val analysisAnnotation = analysisAnnotation(method)
                if (loggerFilter!!.filter(analysisAnnotation) == FilterResult.NEUTRAL) {
                    args.forEach {
                        
                    }
                    method.invoke(loggerServiceImpl, *args)
                }
            } as LoggerService
        }

        private fun analysisAnnotation(method: Method): LoggerLevel {
            val annotations = method.annotations
            if (annotations.isNotEmpty()) {
                annotations.forEach { annotation ->
                    if (annotation is FilterMapping) {
                        return annotation.level
                    }
                }
            }
            return LoggerLevel.ALL
        }

    }
}