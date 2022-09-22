package com.sn.libaray.log.style

import com.sn.libaray.log.layout.LogLayout

open class AbsLoggerLayout : LogLayout {

    override fun format(message: Any): String {
        return message.toString()
    }
}