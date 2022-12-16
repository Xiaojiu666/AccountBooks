package com.gx.task.ui.view.calendar


data class DateDayEntity(var million: Long) {
    var weekName = ""  //周几
    var weekNum = 0  //一周中第几天，非中式
    var date = "" //日期
    var day = ""
    var month = 1
    var year = 1990
    var isToday = false  //是否今天
    var luna = "" //阴历
    var isSelected = false
    var isPlaceholder = false

    override fun toString(): String {
        return "DateEntity(date='$date')"
    }

}
