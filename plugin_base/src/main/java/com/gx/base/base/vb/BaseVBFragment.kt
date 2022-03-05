package com.gx.base.base.vb

import android.view.LayoutInflater
import android.view.View
import androidx.viewbinding.ViewBinding
import com.gx.accountbooks.base.BaseFragment
import com.gx.base.base.getViewBinding

abstract class BaseVBFragment<VB : ViewBinding> : BaseFragment() {

    val mBinding: VB by lazy(mode = LazyThreadSafetyMode.NONE) {
        getViewBinding(layoutInflater)
    }

    override fun initView() {
        mBinding.initBinding()
    }

    override fun getLayoutView(inflater: LayoutInflater): View {
        return mBinding.root
    }

    abstract fun VB.initBinding()

    override fun initData() {

    }
}