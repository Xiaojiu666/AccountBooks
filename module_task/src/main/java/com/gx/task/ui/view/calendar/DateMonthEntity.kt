package com.gx.task.ui.view.calendar

data class DateMonthEntity(var year: Int, var month: Int) {

    var preYear = if (month - 1 < 1) {
        year - 1
    } else {
        year
    }
    var preMonth = if (month - 1 < 1) {
        12
    } else {
        month - 1
    }

    var nextYear = if (month + 1 > 12) {
        year + 1
    } else {
        year
    }
    var nextMonth = if (month + 1 > 12) {
        1
    } else {
        month + 1
    }

    var dayEntityList: ArrayList<DateDayEntity>? = null

    var arrayList = ArrayList<DateMonthEntity>(3)

    fun getRecent3MonthData(): List<DateMonthEntity> {
        arrayList.clear()
        arrayList.add(getPreMonthEntity())
        arrayList.add(getMont4Month(year, month))
        arrayList.add(getNextMonthEntity())
        return arrayList
    }

    fun getPreMonthEntity(): DateMonthEntity {
        return getMont4Month(preYear, preMonth)
    }

    fun getNextMonthEntity(): DateMonthEntity {
        return getMont4Month(nextYear, nextMonth)
    }

    override fun toString(): String {
        return "DateMonthEntity(year=$year, month=$month, preYear=$preYear, preMonth=$preMonth, nextYear=$nextYear, nextMonth=$nextMonth)"
    }


}
