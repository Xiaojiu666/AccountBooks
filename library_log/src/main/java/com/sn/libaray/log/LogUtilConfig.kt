package com.sn.libaray.log

import com.sn.libaray.log.filter.LogFilter
import com.sn.libaray.log.layout.LogLayout
import com.sn.libaray.log.output.AbsOutput
import com.sn.libaray.log.service.AbsLogService

class LogUtilConfig private constructor(
    var logService: AbsLogService?,
    var filter: LogFilter?,
    var messageLayout: LogLayout?,
    var tagLayout: LogLayout?,
    var consoleOpen: Boolean,
    var outPut: AbsOutput?
) {

    init {
        System.loadLibrary("stlport_shared")
        System.loadLibrary("marsxlog")
        logService!!.filter = filter
        logService!!.tagLayout = tagLayout
        logService!!.messageLayout = messageLayout
        logService!!.consoleLogOpen = consoleOpen
        logService!!.output = outPut
        logService!!.initService()
    }

    class Builder(var logService: AbsLogService) {
        var filter: LogFilter? = null
        var messageLayout: LogLayout? = null
        var tagLayout: LogLayout? = null
        var consoleOpen: Boolean = true
        var outPut: AbsOutput? = null

        fun setFilter(filter: LogFilter?): Builder {
            this.filter = filter
            return this
        }

        fun setTagLayout(tagLayout: LogLayout?): Builder {
            this.tagLayout = tagLayout
            return this
        }

        fun setMessageLayout(messageLayout: LogLayout?): Builder {
            this.messageLayout = messageLayout
            return this
        }

        fun setConsole(consoleOpen: Boolean): Builder {
            this.consoleOpen = consoleOpen
            return this
        }

        fun setOutPut(outPut: AbsOutput): Builder {
            this.outPut = outPut
            return this
        }

        fun build(): LogUtilConfig {
            return LogUtilConfig(logService, filter, messageLayout, tagLayout, consoleOpen, outPut)
        }
    }
}