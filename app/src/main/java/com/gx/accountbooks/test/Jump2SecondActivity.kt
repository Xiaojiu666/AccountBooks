package com.gx.accountbooks.test

import android.content.Context
import android.content.Intent
import com.google.auto.service.AutoService
//import com.google.auto.service.AutoService
import com.google.autoservice.IActivity

@AutoService(IActivity::class)
class Jump2SecondActivity : IActivity {

    override fun jump2Activity(context: Context) {
        val intent = Intent(context, SecondActivity::class.java)
        context.startActivity(intent)
    }
}