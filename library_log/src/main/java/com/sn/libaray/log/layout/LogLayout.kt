package com.sn.libaray.log.layout

interface LogLayout {

    fun format(message: Any): String

}