package com.gx.accountbooks.test

import android.content.Context
import android.content.Intent
import com.google.auto.service.AutoService
//import com.google.auto.service.AutoService
import com.google.autoservice.IActivity

@AutoService(IActivity::class)
class Jump2FirstActivity : IActivity {

    override fun jump2Activity(context: Context) {
        val intent = Intent(context, FirstActivity::class.java)
        context.startActivity(intent)
    }
}