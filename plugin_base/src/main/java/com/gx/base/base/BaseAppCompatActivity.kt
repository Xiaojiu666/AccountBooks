package com.gx.base.base

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.gx.base.themeswitcher.ThemeOverlayUtils.applyThemeOverlays

/**
 * Created by GuoXu on 2020/10/13 19:26.
 */
abstract class BaseAppCompatActivity : AppCompatActivity() {

    val TAG: String = javaClass.name

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        applyThemeOverlays(this)
        init()
        setContentView(getLayoutView())
        initView()
    }

    abstract fun init()

    abstract fun initView()

    open fun isHome(): Boolean {
        return false
    }

    // 通过View 绑定，主要为了适用DataBinding
    abstract fun getLayoutView(): View

    fun createView(resId: Int): View {
//        View.inflate(baseContext,R.layout.activity_task,null)
        return LayoutInflater.from(this).inflate(resId, null)
    }

    fun initActionrBar(toolbar: Toolbar) {
        setSupportActionBar(toolbar)
        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.home && !isHome()) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }


}