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
 * Created by GuoXu on 2020/10/13 19:26.
 */
abstract class BaseAppCompatActivity : AppCompatActivity()  {

    val TAG: String = javaClass.name

    @Inject
    lateinit var loadingDialog: LoadingDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        applyThemeOverlays(this)
        setContentView(getLayoutView())
        initView()
    }


    abstract fun initView()

    open fun isHome(): Boolean {
        return false
    }

    // 通过View 绑定，主要为了适用DataBinding
    abstract fun getLayoutView(): View

    fun createView(resId: Int): View {
        return LayoutInflater.from(this).inflate(resId, null)
    }

    fun initActionBar(toolbar: Toolbar) {
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.home && !isHome()) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }


}