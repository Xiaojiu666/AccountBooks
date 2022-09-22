package com.sn.libaray.log.service

import com.sn.libaray.log.FilterMapping
import com.sn.libaray.log.TAG
import com.sn.libaray.log.filter.AbstractFilter
import com.sn.libaray.log.filter.LogFilter
import com.sn.libaray.log.filter.LoggerLevel
import com.sn.libaray.log.layout.LogLayout

interface LoggerService {


    fun verbose(message: Any)

    fun verbose(tag: String, message: Any)

    fun verbose(tag: String, message: Any, vararg params: String)

    fun debug(message: String)

    fun info(message: String)

    fun warn(message: String)

    fun error(message: String)

    fun error(tag: String, message: Any)
}