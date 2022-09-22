package com.sn.libaray.log.output

import java.io.File

class AbsOutput(var fileFile: File, var cacheFile: File) {

    companion object{
        fun create(fileFile: File, cacheFile: File): AbsOutput {
            return AbsOutput(fileFile, cacheFile)
        }
    }


}