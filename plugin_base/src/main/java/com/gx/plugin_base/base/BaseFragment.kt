package com.gx.accountbooks.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

/**
 * Created by GuoXu on 2020/10/14 16:34.
 *
 */
abstract class BaseFragment : Fragment() {

    val TAG  by lazy { this.javaClass.name }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return getLayoutView(inflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
    }

    abstract fun initView(view: View)


    abstract fun getLayoutView(inflater: LayoutInflater): View?
}