package com.gx.base.base.vb

import android.view.View
import androidx.viewbinding.ViewBinding
import com.gx.base.base.BaseAppCompatActivity
import com.gx.base.base.getViewBinding

abstract class BaseVBActivity<VB : ViewBinding> : BaseAppCompatActivity() {

    val mBinding: VB by lazy(mode = LazyThreadSafetyMode.NONE) {
        getViewBinding(layoutInflater)
    }

    override fun initView() {
        mBinding.initBinding()
    }

    override fun getLayoutView(): View {
        return mBinding.root
    }

    abstract fun VB.initBinding()

    override fun initData() {

    }

}