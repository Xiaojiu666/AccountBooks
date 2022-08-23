package com.sn.libaray.log

import android.util.Log
import com.sn.libaray.log.LoggerService
import java.lang.reflect.Proxy

class Test(private val loggerService: LoggerService) {


    fun create(): LoggerService {
        val loggerServiceClass = LoggerService::class.java
        return Proxy.newProxyInstance(
            loggerServiceClass.classLoader, arrayOf<Class<*>>(loggerServiceClass)
        ) { proxy, method, args ->
            Log.d("proxy.TAG", "Test invoke")
            method.invoke(loggerService, *args)
        } as LoggerService
    }
}