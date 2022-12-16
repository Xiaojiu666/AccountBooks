package com.gx.task.ui.view.calendar.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.gx.module_task.R
import com.gx.task.ui.view.calendar.DateDayEntity
import com.gx.task.ui.view.calendar.interfacies.OnDayClickListener
import com.gx.task.ui.view.calendar.interfacies.SelectorDayManager
import com.sn.libaray.log.LogUtils
import com.sn.libaray.log.TAG
import org.jetbrains.anko.db.NULL

class DayRvAdapter(var parentPosition: Int) :
    RecyclerView.Adapter<DayRvAdapter.WeekVH>() {

    private var dateData: ArrayList<DateDayEntity> = ArrayList()
    var onDayClickListener: OnDayClickListener? = null
    var selectorPosition = 0


    fun upData(dateDates: ArrayList<DateDayEntity>) {
        dateData.addAll(dateDates)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeekVH {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_calendar_day, parent, false)
        return WeekVH(view)
    }


    override fun onBindViewHolder(holder: WeekVH, position: Int) {
        val dateDayEntity = dateData[position]
        holder.itemTvWeek.text = dateDayEntity.day
        if (dateDayEntity.isPlaceholder) {
            return
        }
        if (dateDayEntity.isToday) {
            holder.itemTvWeek.setTextColor(holder.rootView.context.getColor(R.color.colorAppTheme))
        } else {
            holder.itemTvWeek.setTextColor(holder.rootView.context.getColor(R.color.base_color_black))
        }
        var selectorDayEntity: DateDayEntity? = SelectorDayManager.instance.selectorDayEntity
        LogUtils.d(TAG, "selectorDayEntity $selectorDayEntity , dateDayEntity$dateDayEntity")
        if (selectorDayEntity == dateDayEntity) {
            holder.rootView.background =
                ContextCompat.getDrawable(holder.rootView.context, R.drawable.side_nav_bar)
        } else {
            holder.rootView.background =
                ContextCompat.getDrawable(holder.rootView.context, R.color.color_white)
        }
        holder.rootView.setOnClickListener {
            SelectorDayManager.instance.selectorDayEntity = dateData[position]
            notifyItemChanged(selectorPosition)
            selectorPosition = position
            notifyItemChanged(position)
            if (onDayClickListener != null) {
                onDayClickListener!!.onDayClick(parentPosition, position, dateDayEntity)
            }
        }
    }

    override fun getItemCount(): Int {
        return dateData.size
    }

    class WeekVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemTvWeek: TextView
        val rootView: ConstraintLayout

        init {
            itemTvWeek = itemView.findViewById(R.id.item_tv_day)
            rootView = itemView.findViewById(R.id.root_view)
        }
    }
}