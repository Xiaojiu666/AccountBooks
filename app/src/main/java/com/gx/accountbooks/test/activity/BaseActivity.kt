package com.gx.accountbooks.test.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gx.utils.log.LogUtil

class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LogUtil.d(TAG, "onCreate ")
    }

    override fun onStart() {
        super.onStart()
        LogUtil.d(TAG, "onStart ")
    }

    override fun onResume() {
        super.onResume()
        LogUtil.d(TAG, "onResume ")
    }

    override fun onPause() {
        super.onPause()
        LogUtil.d(TAG, "onPause ")
    }

    override fun onStop() {
        super.onStop()
        LogUtil.d(TAG, "onStop ")
    }

    override fun onDestroy() {
        super.onDestroy()
        LogUtil.d(TAG, "onDestroy ")
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        LogUtil.d(TAG, "onNewIntent ")
    }
}