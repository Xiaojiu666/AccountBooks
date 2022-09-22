package com.gx.base.application

import android.app.Application
import android.content.Context
import android.os.Environment
import com.alibaba.android.arouter.launcher.ARouter
import com.gx.base.File.FileManager
import com.gx.base.config.AppConfig
import com.gx.base.config.AppFileConfig
import com.gx.plugin_common.BuildConfig
import com.gx.utils.apk.AppInfoUtil
import com.sn.libaray.log.LogUtilConfig
import com.sn.libaray.log.LogUtils
import com.sn.libaray.log.TAG
import com.sn.libaray.log.filter.LoggerLevel
import com.sn.libaray.log.filter.LoggerLevelFilter
import com.sn.libaray.log.layout.DefaultLogLayout
import com.sn.libaray.log.output.AbsOutput
import com.sn.libaray.log.service.AbsLogService
import com.sn.libaray.log.service.XLogService
import com.sn.libaray.log.style.GsonLayout
import java.io.File

abstract class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initBasePlugin()
        initRoom(applicationContext)
        initARouter()
    }

    private fun initARouter() {
        if (BuildConfig.DEBUG) {
            //openDebug 必须在init之前，他决定是否从缓存中读取数据
            ARouter.openLog()
            ARouter.openDebug()
        }
        ARouter.init(this@BaseApplication)
    }

    abstract fun initRoom(application: Context)

    open fun initBasePlugin() {
        initConfig()
        initFile()
        initLogUtil()
        LogUtils.v("BaseApplication onCreate")
    }

    private fun initConfig() {
        AppConfig.initConfig(applicationContext)
    }

    private fun initFile() {
        FileManager.createRootFile(AppConfig.ROOT_PATH)
        FileManager.createRootFile(AppFileConfig.LOG_PATH)
    }

    private fun initLogUtil() {
        val logUtilConfig = LogUtilConfig.Builder(XLogService())
            .setConsole(true)
            .setFilter(LoggerLevelFilter.createFilter(LoggerLevel.ALL))
            .setTagLayout(DefaultLogLayout())
            .setMessageLayout(GsonLayout())
            .setOutPut(
                AbsOutput.create(
                    File(AppFileConfig.LOG_XLOG_PATH),
                    File(AppFileConfig.LOG_XLOG_CACHE_PATH)))
            .build()
        LogUtils.initConfig(logUtilConfig)
//        LogUtil.initLog(AppFileConfig.FILE_XLOG, AppFileConfig.FILE_XLOG_CACHE)
//        LogUtil.printHeader(applicationContext)
    }

    override fun onTerminate() {
        super.onTerminate()
        ARouter.getInstance().destroy()
    }
}