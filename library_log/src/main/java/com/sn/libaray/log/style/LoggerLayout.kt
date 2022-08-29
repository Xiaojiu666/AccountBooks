package com.sn.libaray.log.style

interface LoggerLayout {

    fun format(message: CharSequence): CharSequence

}