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

    fun debug(message: Any)

    fun info(message: Any)

    fun warn(message: Any)

    fun error(message: Any)

    fun error(tag: String, message: Any)
}