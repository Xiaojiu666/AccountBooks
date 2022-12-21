package com.gx.base.base

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.gx.base.themeswitcher.ThemeOverlayUtils.applyThemeOverlays
import com.gx.ui.dialog.LoadingDialog
import javax.inject.Inject

/**
 *  最上层基类Activity
 */
abstract class BaseAppCompatActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //设置主题
        applyThemeOverlays(this)
        //设置布局
        setContentView(getLayoutView())
        initView()
        initData()
    }

    abstract fun initData()

    abstract fun initView()

    // 通过View 绑定，主要为了适用DataBinding
    abstract fun getLayoutView(): View

    fun createView(resId: Int): View {
        return LayoutInflater.from(this).inflate(resId, null)
    }

//    fun initActionBar(toolbar: Toolbar) {
//        setSupportActionBar(toolbar)
//        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
//        supportActionBar!!.setDisplayShowTitleEnabled(false)
//    }
}