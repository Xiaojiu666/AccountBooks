package com.gx.task.repository.data

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gx.room.RoomConfig.PLAN_TABLE_NAME
import com.gx.room.RoomConfig.TASK_TABLE_NAME
import com.gx.room.RoomConfig.TASK_TABLE_PRIMARY_KEY
import kotlinx.android.parcel.Parcelize


@Entity(tableName = PLAN_TABLE_NAME)
@Parcelize
data class Plan(var title: String) : Parcelable  {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = TASK_TABLE_PRIMARY_KEY)
    var planId: Long = 0

    var planStartTime: Long? = 0L
    var planEndTime: Long? = 0L

    var planListTotalSize: Int = 0
    var planListCompleteSize: Int = 0

    // 0 进行中 1 已完成 2 延期
    var planListStatus: Int = 0
    var planDescription: String? = null

    override fun toString(): String {
        return "Plan(title='$title', planId=$planId, planStartTime=$planStartTime, planEndTime=$planEndTime, planListTotalSize=$planListTotalSize, planListCompleteSize=$planListCompleteSize, planListStatus=$planListStatus, planDescription=$planDescription)"
    }


}