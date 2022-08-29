package com.gx.accountbooks.test.activity

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gx.accountbooks.R
import com.gx.utils.log.LogUtil
import com.gx.utils.log.LogUtil.TAG
import com.sn.libaray.log.TAG
import kotlinx.android.synthetic.main.test_activity_first.*

open class BaseActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.test_activity_first)
        LogUtil.d(TAG, "onCreate ")
        btn_first.setOnClickListener {
            startFirstActivity()
        }
        btn_top.setOnClickListener {
            startSingleTopActivity()
        }
        btn_singleTask.setOnClickListener {
            startSingleTaskActivity()
        }
        btn_instance.setOnClickListener {
            starSingleInstanceActivity()
        }
        tv_name.text = TAG + taskId
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

    open fun startFirstActivity() {
        var intent = Intent(this, FirstActivity::class.java)
        startActivity(intent)
    }
    open fun startSingleTaskActivity() {
        var intent = Intent(this, SingleTaskActivity::class.java)
        startActivity(intent)
    }
    open fun startSingleTopActivity() {
        var intent = Intent(this, SingleTopActivity::class.java)
        startActivity(intent)
    }
    open fun starSingleInstanceActivity() {
        var intent = Intent(this, SingleInstanceActivity::class.java)
        startActivity(intent)
    }
}