package com.gx.task.ui.view.calendar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gx.module_task.R

class MonthRvAdapter : RecyclerView.Adapter<MonthRvAdapter.WeekVH>() {

    private var monthEntityList: ArrayList<DateMonthEntity> = ArrayList()

    fun upData(dateDates: List<DateMonthEntity>) {
        monthEntityList.clear()
        monthEntityList.addAll(dateDates)
    }

    fun toUp() {
        notifyItemRemoved(0)
        notifyItemInserted(2)
    }

    fun toDown() {
        notifyItemRemoved(2)
        notifyItemInserted(0)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeekVH {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_calendar_month, parent, false)
        return WeekVH(view)
    }

    override fun onBindViewHolder(holder: WeekVH, position: Int) {
        var monthEntity = monthEntityList[position]
        val month = "${monthEntity.year} 年 ${monthEntity.month} 月 "
        holder.itemTvWeek.text = month
        holder.recyclerView.layoutManager =
            GridLayoutManager(holder.recyclerView.context, WeekRvAdapter.weekData.size)
        val dayRvAdapter = DayRvAdapter()
        dayRvAdapter.upData(monthEntity.dayEntityList!!)
        holder.recyclerView.adapter = dayRvAdapter
    }

    override fun getItemCount(): Int {
        return monthEntityList.size
    }


    class WeekVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemTvWeek: TextView
        val recyclerView: RecyclerView

        init {
            // Define click listener for the ViewHolder's View.
            itemTvWeek = itemView.findViewById(R.id.textView10)
            recyclerView = itemView.findViewById(R.id.recyclerView_day)
        }
    }
}