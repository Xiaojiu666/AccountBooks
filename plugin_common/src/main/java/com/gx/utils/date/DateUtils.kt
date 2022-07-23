package com.gx.utils.date

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

const val PATTERN_Y_M_D = "yyyy-MM-dd"
const val PATTERN_Y_M_D_H_M_S = "yyyy-MM-dd HH:mm:ss"

@SuppressLint("SimpleDateFormat")
fun Long.toDateStr(pattern: String = "yyyy-MM-dd HH:mm:ss"): String {
    val date = Date(this)
    val format = SimpleDateFormat(pattern)
    return format.format(date)
}

