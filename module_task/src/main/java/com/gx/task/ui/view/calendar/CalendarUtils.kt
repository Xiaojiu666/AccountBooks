package com.gx.task.ui.view.calendar

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.text.TextUtils
import com.sn.libaray.log.LogUtils
import com.sn.libaray.log.TAG
import java.text.ParseException
import java.util.*
import kotlin.math.max


var TAG = "CalendarUtils"

val s = "yyyy-MM-dd"

var dateFormat = SimpleDateFormat(s);

val cal: Calendar = Calendar.getInstance()

fun getMont4Month(
    year: Int = cal.get(Calendar.YEAR),
    month: Int = cal.get(Calendar.MONTH) + 1
): DateMonthEntity {
    val monthEntity = DateMonthEntity(year, month)
    monthEntity.dayEntityList = getDays4YearMonth(year, month)
    LogUtils.d(
        TAG,
        "currentMonthEntity toDown year： ${monthEntity.year}  ,month：${monthEntity.month} , dayEntityLists：${monthEntity.dayEntityList!!.size}  , dayEntityList：${monthEntity.dayEntityList} "
    )
    return monthEntity
}

/**
 * 获取当前日期一月的日期
 * @param date
 * @return
 */
fun getDays4YearMonth(
    year: Int = cal.get(Calendar.YEAR),
    month: Int = cal.get(Calendar.MONTH) + 1
): ArrayList<DateDayEntity> {
    val result: ArrayList<DateDayEntity> = ArrayList()
    try {
        // 初始化日历时间
        cal.time = getTimeDate("$year:$month:01 00:00:00")
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    val max: Int = cal.getActualMaximum(Calendar.DAY_OF_MONTH)
    for (day in 1..max) {
        val entity = DateDayEntity(cal.timeInMillis)
        val num = cal.get(Calendar.DATE)
        entity.date = getValue(year) + "-" + month + "-" + getValue(num)
        entity.weekNum = cal.get(Calendar.DAY_OF_WEEK)
        entity.day = getValue(num)
        entity.weekName = getWeekName(entity.weekNum)
        entity.isToday = isToday(entity.date)
        entity.luna = "AAA"
        cal.add(Calendar.DATE, 1)
        result.add(entity)
    }
    //为了用空的值填补第一个之前的日期
    //先获取在本周内是周几
    val weekNum: Int = result[0].weekNum - 1
    for (j in 0 until weekNum) {
        val entity = DateDayEntity(cal.timeInMillis)
        result.add(0, entity)
    }
    return result
}

fun getValue(num: Int): String {
    return if (num > 9) num.toString() else "0$num"
}

/**
 * 根据美式周末到周一 返回
 * @param weekNum
 * @return
 */
fun getWeekName(weekNum: Int): String {
    var name = ""
    when (weekNum) {
        1 -> name = "星期日"
        2 -> name = "星期一"
        3 -> name = "星期二"
        4 -> name = "星期三"
        5 -> name = "星期四"
        6 -> name = "星期五"
        7 -> name = "星期六"
        else -> {}
    }
    return name
}

fun isToday(sdate: String?): Boolean {
    var b = false
    var time: Date? = null
    try {
        time = dateFormat.parse(sdate)
    } catch (e: ParseException) {
        // TODO Auto-generated catch block
        e.printStackTrace()
    }
    val today = Date()
    if (time != null) {
        val nowDate: String = dateFormater.get().format(today)
        val timeDate: String = dateFormater.get().format(time)
        if (nowDate == timeDate) {
            b = true
        }
    }
    return b
}


var dateFormater = object : ThreadLocal<SimpleDateFormat>() {
    @SuppressLint("SimpleDateFormat")
    override fun initialValue(): SimpleDateFormat {
        return SimpleDateFormat(s)
    }
}


@SuppressLint("SimpleDateFormat")
fun getTime(timeString: String?): String? {
    var timeStamp: String? = null
    val sdf = SimpleDateFormat("yyyy:MM:dd hh:mm:ss")
    val d: Date
    try {
        d = sdf.parse(timeString)
        val l = d.time
        timeStamp = l.toString()
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    return timeStamp
}

@SuppressLint("SimpleDateFormat")
fun getTimeDate(timeString: String?): Date? {
    var timeStamp: String? = null
    val sdf = SimpleDateFormat("yyyy:MM:dd hh:mm:ss")
    var d: Date? = null
    try {
        d = sdf.parse(timeString)
        val l = d.time
        timeStamp = l.toString()
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    return d
}


