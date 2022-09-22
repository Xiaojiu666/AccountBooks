package com.sn.libaray.log.layout

import com.sn.libaray.log.layout.LogLayout

open class TagLayout : LogLayout {


    override fun format(message: Any): String {
        return message.toString()
    }
}