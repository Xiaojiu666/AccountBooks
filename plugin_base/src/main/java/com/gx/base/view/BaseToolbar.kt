package com.gx.base.view

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.Toolbar

class BaseToolbar : Toolbar {
    constructor(context: Context) : super(context) {}
    constructor(
        context: Context,
        attrs: AttributeSet?
    ) : super(context, attrs) {
    }
    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr) {
    }
}