package com.sn.libaray.log.layout

class DefaultLogLayout : LogLayout {

    override fun format(message: Any): String {
        return message.toString()
    }
}