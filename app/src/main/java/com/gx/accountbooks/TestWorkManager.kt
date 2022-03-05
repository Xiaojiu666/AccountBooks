package com.gx.accountbooks

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters

class TestWorkManager(context: Context,
                      workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork( ): Result {
        //TODO
       return Result.failure()
    }
}