package com.gx.base.base

import android.view.View
import androidx.databinding.ViewDataBinding

abstract class BaseVBActivity<VB : ViewDataBinding> : BaseAppCompatActivity() {

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

}