package com.gx.accountbooks.test

import android.content.Context
import android.content.Intent
import com.google.auto.service.AutoService
//import com.google.auto.service.AutoService
import com.google.autoservice.IActivity
import com.gx.accountbooks.test.activity.SingleTopActivity

@AutoService(IActivity::class)
class Jump2SecondActivity : IActivity {

    override fun jump2Activity(context: Context) {
        val intent = Intent(context, SingleTopActivity::class.java)
        context.startActivity(intent)
    }
}