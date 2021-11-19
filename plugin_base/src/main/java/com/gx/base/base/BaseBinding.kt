package com.gx.base.base

import androidx.databinding.ViewDataBinding

interface BaseBinding<VB: ViewDataBinding> {
    fun VB.initBinding()
}