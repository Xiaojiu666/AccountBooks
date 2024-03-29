package com.gx.base.base

import android.view.LayoutInflater
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType


inline fun <VB: ViewBinding> Any.getViewBinding(inflater: LayoutInflater):VB{
    val vbClass =  (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments.filterIsInstance<Class<VB>>()
    val inflate = vbClass[0].getDeclaredMethod("inflate", LayoutInflater::class.java)
    return  inflate.invoke(null, inflater) as VB
}
