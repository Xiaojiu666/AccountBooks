package com.gx.task.ui.view.calendar

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.gx.module_task.R
import com.gx.task.ui.view.calendar.adapter.MonthRvAdapter
import com.gx.task.ui.view.calendar.adapter.WeekRvAdapter
import com.sn.libaray.log.LogUtils


class CalendarView(context: Context, attributes: AttributeSet?) :
    LinearLayout(context, attributes) {


    var changeRyLayout = false
    lateinit var monthRvAdapter: MonthRvAdapter
    lateinit var currentMonthEntity: DateMonthEntity
    lateinit var recent3MonthData: List<DateMonthEntity>
    var linearLayoutManager: LinearLayoutManager? = null
    var calendarDayRvView: RecyclerView? = null

    init {
        val calendarView = View.inflate(context, R.layout.view_calendar, this)
        val calendarWeekRvView = calendarView.findViewById<RecyclerView>(R.id.calender_rv_week)
        calendarDayRvView = calendarView.findViewById<RecyclerView>(R.id.calender_rv_day)
        initData()
        initRvWeekHeader(calendarWeekRvView)
        initRvDay()
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val widthSpecMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSpecSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightSpecMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSpecSize = MeasureSpec.getSize(heightMeasureSpec)
        LogUtils.e(TAG, "widthSpecMode $widthSpecMode ，widthSpecSize$widthSpecSize")
        LogUtils.e(TAG, "heightSpecMode $heightSpecMode ，heightSpecSize$heightSpecSize")
//        setMeasuredDimension(widthSpecSize, heightSpecSize)
    }

    private fun initData() {
        currentMonthEntity = getMont4Month()
        LogUtils.e("month4CurrentTime $currentMonthEntity")
        recent3MonthData = currentMonthEntity.getRecent3MonthData()
        monthRvAdapter = MonthRvAdapter()
    }

    private fun initRvWeekHeader(calendarRvView: RecyclerView) {
        calendarRvView.layoutManager = GridLayoutManager(context, WeekRvAdapter.weekData.size)
        calendarRvView.adapter = WeekRvAdapter()
    }

    private fun initRvDay() {
        monthRvAdapter.upData(recent3MonthData)
        linearLayoutManager = LinearLayoutManager(context)
        val pagerSnapHelper = PagerSnapHelper()
        pagerSnapHelper.attachToRecyclerView(calendarDayRvView)
        calendarDayRvView!!.layoutManager = linearLayoutManager
        calendarDayRvView!!.adapter = monthRvAdapter
        //重新计算 RecyclerView 的宽高
        calendarDayRvView!!.viewTreeObserver.addOnGlobalLayoutListener {
            if (changeRyLayout) {
                return@addOnGlobalLayoutListener
            }
            calendarDayRvView!!.scrollToPosition(1)
            calendarDayRvView!!.addOnScrollListener(recyclerScrollListener)
            changeRyLayout = true
        }
    }

    private val recyclerScrollListener: RecyclerView.OnScrollListener =
        object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == 0) {
                    val layoutManager = recyclerView.layoutManager as LinearLayoutManager?
                    if (layoutManager != null) {
                        val firstVisible = layoutManager.findFirstVisibleItemPosition()
                        val view = recyclerView.layoutManager!!.findViewByPosition(firstVisible)
                        LogUtils.d(
                            TAG,
                            "linearLayoutManager width = ${view!!.width} , height =  ${view!!.height}"
                        )
                        var params = calendarDayRvView!!.layoutParams
                        params.height = view.height
                        calendarDayRvView!!.layoutParams = params
                        if (firstVisible > 1) {
                            currentMonthEntity = currentMonthEntity.getNextMonthEntity()
                            recent3MonthData = currentMonthEntity.getRecent3MonthData()
                            monthRvAdapter.upData(recent3MonthData)
                            monthRvAdapter.toUp()
                        } else if (firstVisible < 1) {
                            currentMonthEntity = currentMonthEntity.getPreMonthEntity()
                            recent3MonthData = currentMonthEntity.getRecent3MonthData()
                            monthRvAdapter.upData(recent3MonthData)
                            monthRvAdapter.toDown()
                        }
                    }

                }
            }
        }
}