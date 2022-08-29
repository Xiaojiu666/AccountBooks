package com.sn.libaray.log.style

import org.json.JSONArray
import org.json.JSONObject

class JsonLoggerLayout : AbsLoggerLayout() {
    override fun format(message: CharSequence): CharSequence {
        return formatDataFromJson(message as String)!!
    }

    fun formatDataFromJson(response: String): String? {
        try {
            if (response.startsWith("{")) {
                val jsonObject = JSONObject(response)
                return jsonObject.toString(3)
            } else if (response.startsWith("[")) {
                val jsonArray = JSONArray(response)
                return jsonArray.toString(3)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return response
    }

}