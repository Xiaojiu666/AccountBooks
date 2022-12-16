package com.gx.task.ui.view.calendar.interfacies

import com.gx.task.ui.view.calendar.DateDayEntity

class SelectorDayManager private constructor() {

    var selectorDayEntity: DateDayEntity? = null

    companion object {
        val instance = SingletonHolder.holder
    }

    private object SingletonHolder {
        val holder = SelectorDayManager()
    }

}