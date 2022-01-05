package com.gx.accountbooks.test

import android.os.Build
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.N)
class TestList : TestActivity() {

    var list = mutableListOf("1",2,3,4L,4.0)
    val map1 = mapOf("key1" to 1,"key2" to 2)

    init {
        map1.forEach{ (key, value) -> println("$key \t $value") }
    }
}