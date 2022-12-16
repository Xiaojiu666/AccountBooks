package com.gx.task.ui.view.calendar.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gx.module_task.R

class WeekRvAdapter : RecyclerView.Adapter<WeekRvAdapter.WeekVH>() {

    companion object {
        var weekData =arrayOf("日","一","二","三","四","五","六")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeekVH {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_calendar, parent, false)
        return WeekVH(view)
    }

    override fun onBindViewHolder(holder: WeekVH, position: Int) {
        holder.itemTvWeek.text = weekData[position]
    }

    override fun getItemCount(): Int {
        return weekData.size
    }


    class WeekVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemTvWeek: TextView
        init {
            // Define click listener for the ViewHolder's View.
            itemTvWeek = itemView.findViewById(R.id.item_tv_week)
        }
    }
}