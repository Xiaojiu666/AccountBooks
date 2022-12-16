package com.gx.task.ui.view.calendar.interfacies

import android.view.View
import com.gx.task.ui.view.calendar.DateDayEntity

interface OnDayClickListener {
    fun onDayClick(parentPosition: Int, position: Int, dayEntity: DateDayEntity)
}