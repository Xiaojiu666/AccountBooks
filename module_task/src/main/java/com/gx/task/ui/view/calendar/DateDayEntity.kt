package com.gx.task.ui.view.calendar

data class DateDayEntity(var million: Long) {
    var weekName = ""  //周几
    var weekNum = 0  //一周中第几天，非中式
    var date = "" //日期
    var day = ""
    var isToday = false  //是否今天
    var luna = "" //阴历


    override fun toString(): String {
        return "DateEntity(million=$million, weekName='$weekName', weekNum=$weekNum, date='$date', day='$day', isToday=$isToday, luna='$luna')"
    }

}
