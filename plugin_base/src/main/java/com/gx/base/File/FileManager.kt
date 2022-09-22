package com.gx.base.File

import android.text.TextUtils
import android.util.Log
import com.sn.libaray.log.LogUtils
import com.sn.libaray.log.TAG
import org.w3c.dom.Text
import java.io.File

object FileManager {

    fun createRootFile(filePath: String, fileName: String = ""): File? {
        if (TextUtils.isEmpty(filePath)) {
            Log.e(TAG, "rootFile is exists")
            return null
        }
        val rootFile = File(filePath)
        if (rootFile.exists()) {
            Log.v(TAG, "rootFile is exists")
        }
        rootFile.mkdirs()
        return rootFile
    }

}