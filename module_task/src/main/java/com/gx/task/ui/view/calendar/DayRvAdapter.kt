package com.gx.task.ui.view.calendar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gx.module_task.R

class DayRvAdapter : RecyclerView.Adapter<DayRvAdapter.WeekVH>() {

    private var dateData: ArrayList<DateDayEntity> = ArrayList()

    fun upData(dateDates: ArrayList<DateDayEntity>){
        dateData.addAll(dateDates)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeekVH {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_calendar_day, parent, false)
        return WeekVH(view)
    }

    override fun onBindViewHolder(holder: WeekVH, position: Int) {
        holder.itemTvWeek.text = dateData[position].day
    }

    override fun getItemCount(): Int {
        return dateData.size
    }


    class WeekVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemTvWeek: TextView

        init {
            // Define click listener for the ViewHolder's View.
            itemTvWeek = itemView.findViewById(R.id.item_tv_day)
        }
    }
}