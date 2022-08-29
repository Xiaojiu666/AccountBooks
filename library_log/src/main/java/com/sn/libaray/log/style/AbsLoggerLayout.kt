package com.sn.libaray.log.style

open class AbsLoggerLayout : LoggerLayout {

    override fun format(message: CharSequence): CharSequence {
        return message
    }
}