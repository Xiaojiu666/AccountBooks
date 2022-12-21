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
    //通过扩展函数，给ViewBinding扩展一个初始化的方法。
    //在里面可以获取ViewBinding的所有属性，包括我们的Id
    abstract fun VB.initBinding()

    override fun initData() {

    }

}