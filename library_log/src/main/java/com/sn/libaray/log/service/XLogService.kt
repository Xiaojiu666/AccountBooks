package com.sn.libaray.log.service

import com.sn.libaray.log.TAG
import com.tencent.mars.xlog.Xlog

class XLogService(var pubKey: String = "") : AbsLogService() {

    override fun initService() {
        super.initService()
        val filePath = output!!.fileFile.path
        val cachePath = output!!.cacheFile.path
        Xlog.appenderOpen(
            Xlog.LEVEL_VERBOSE,
            Xlog.AppednerModeAsync,
            filePath,
            cachePath,
            TAG,
            pubKey
        )
        Xlog.setConsoleLogOpen(true)
        Xlog.appenderFlush(true)
    }


    override fun verbose(message: Any) {
        val ste = Thread.currentThread().stackTrace[5]
        super.verbose(ste.fileName, message)
        val xLoggerInfo = Xlog.XLoggerInfo()
        xLoggerInfo.tag = ste.fileName
        Xlog.logWrite(xLoggerInfo, messageLayout!!.format(message.toString()))
    }

}