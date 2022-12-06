package com.gx.task.ui.view.calendar

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.*
import com.gx.module_task.R
import com.sn.libaray.log.LogUtils
import com.sn.libaray.log.TAG


class CalendarView(context: Context, attributes: AttributeSet?) :
    LinearLayout(context, attributes) {


    var changeRyLayout = false
    lateinit var monthRvAdapter: MonthRvAdapter
    lateinit var currentMonthEntity: DateMonthEntity
    lateinit var recent3MonthData: List<DateMonthEntity>


    init {
        val calendarView = View.inflate(context, R.layout.view_calendar, this)
        val calendarWeekRvView = calendarView.findViewById<RecyclerView>(R.id.calender_rv_week)
        val calendarDayRvView = calendarView.findViewById<RecyclerView>(R.id.calender_rv_day)
        initData()
        initRvWeekHeader(calendarWeekRvView)
        initRvDay(calendarDayRvView)
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

    private fun initRvDay(calendarDayRvView: RecyclerView) {
        monthRvAdapter.upData(recent3MonthData)
        val linearLayoutManager = LinearLayoutManager(context)
        val pagerSnapHelper = PagerSnapHelper()
        pagerSnapHelper.attachToRecyclerView(calendarDayRvView)
        calendarDayRvView.layoutManager = linearLayoutManager
        calendarDayRvView.adapter = monthRvAdapter
        //重新计算 RecyclerView 的宽高
        calendarDayRvView.viewTreeObserver.addOnGlobalLayoutListener {
            if (changeRyLayout) {
                return@addOnGlobalLayoutListener
            }
            var snapHelper = pagerSnapHelper.findSnapView(linearLayoutManager)
            var params = calendarDayRvView.layoutParams
            params.height = snapHelper!!.height
            calendarDayRvView.layoutParams = params
            calendarDayRvView.scrollToPosition(1)
            calendarDayRvView.addOnScrollListener(recyclerScrollListener)
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