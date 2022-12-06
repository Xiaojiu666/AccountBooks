package com.gx.task.repository.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class PlanInfo(var name: String) : Parcelable {

    var planId = -1

//    var planType = 0  // 0 等于普通计划  ， 1 = 周期性计划 ，周期性通过创建计划得来，不能选择名称

    override fun toString(): String {
        return "PlanInfo(name='$name', planId=$planId)"
    }


}
