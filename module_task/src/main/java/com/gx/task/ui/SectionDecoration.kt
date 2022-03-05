package com.gx.task.ui

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import android.text.TextPaint
import com.gx.data.task.Task
import android.R
import android.content.res.Resources
import android.graphics.*
import android.text.TextUtils

import com.gx.utils.log.LogUtil


class SectionDecoration(var context: Context, var callback: DecorationCallback) :
    RecyclerView.ItemDecoration() {

    private var textPaint: TextPaint = TextPaint()
    private var paint: Paint = Paint()
    private var topGap = 32
    private val fontMetrics: Paint.FontMetrics = Paint.FontMetrics()

    init {
        val res: Resources = context.resources
        paint.color = res.getColor(R.color.white)
        textPaint.typeface = Typeface.DEFAULT_BOLD
        textPaint.isAntiAlias = true
        textPaint.textSize = 64f
        textPaint.color = Color.BLACK
        textPaint.getFontMetrics(fontMetrics)
        textPaint.textAlign = Paint.Align.LEFT
        topGap = res.getDimensionPixelSize(R.dimen.app_icon_size)

    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val childAdapterPosition = parent.getChildAdapterPosition(view)
        if (childAdapterPosition == 0 || isFirstInGroup(childAdapterPosition)) {//同组的第一个才添加padding
            outRect.top = topGap;
        } else {
            outRect.top = 0;
        }
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
        val left: Int = parent.getPaddingLeft()
        val right: Int = parent.getWidth() - parent.getPaddingRight()
        val childCount: Int = parent.getChildCount()
        for (i in 0 until childCount) {
            val view: View = parent.getChildAt(i)
            val position: Int = parent.getChildAdapterPosition(view)
            val groupId: Task = callback.getGroupId(position)
            val textLine = if (groupId.taskStatus == 0) {
                "未完成"
            } else {
                "已完成"
            }
            if (position == 0 || isFirstInGroup(position)) {
                val top = (view.top - topGap).toFloat()
//                LogUtil.d("onDraw view left = ${view.left} , top = ${view.top}  , right = ${view.right}  , bottom = ${view.bottom}")
                val bottom = view.top.toFloat()
//                LogUtil.d("onDraw left = ${left.toFloat()} , top = ${top}  , right = ${right}  , bottom = ${bottom}")
                c.drawRect(left.toFloat(), top, right.toFloat(), bottom, paint) //绘制红色矩形
                c.drawText(textLine, left.toFloat() + 20, bottom - 40, textPaint) //绘制文本
            }
        }
    }

    private fun isFirstInGroup(pos: Int): Boolean {
        return if (pos == 0) {
            true
        } else {
            val prevGroupId: Task = callback.getGroupId(pos - 1)
            val groupId: Task = callback.getGroupId(pos)
            prevGroupId.taskStatus != groupId.taskStatus
        }
    }

    interface DecorationCallback {
        fun getGroupId(position: Int): Task
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)
        val itemCount = state.itemCount
        val childCount = parent.childCount
        val left = parent.paddingLeft
        val right = parent.width - parent.paddingRight
        val lineHeight = textPaint.textSize + fontMetrics.descent
        var preGroupId: Task? = null
        var groupId: Task? = null
        for (i in 0 until childCount) {
            val view = parent.getChildAt(i)
            val position = parent.getChildAdapterPosition(view)
            preGroupId = groupId
            groupId = callback.getGroupId(position)
            if (preGroupId == null || groupId.taskStatus == preGroupId.taskStatus) continue

            val textLine = if (groupId.taskStatus == 0) {
                "未完成"
            } else {
                "已完成"
            }
            if (TextUtils.isEmpty(textLine)) continue
            val viewBottom = view.bottom
            var textY = Math.max(topGap, view.top).toFloat()
            if (position + 1 < itemCount) { //下一个和当前不一样移动当前
                val nextGroupId: Task = callback.getGroupId(position + 1)
                if (nextGroupId.taskStatus != groupId.taskStatus && viewBottom < textY) { //组内最后一个view进入了header
                    textY = viewBottom.toFloat()
                }
            }
            c.drawRect(left.toFloat(), textY - topGap, right.toFloat(), textY, paint)
            c.drawText(textLine, left.toFloat() + 20, textY - 40, textPaint)
        }
    }

}