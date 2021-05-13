package com.sevenzeroes.trekkieapp.core.helpers

import android.os.Build
import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.sevenzeroes.trekkieapp.core.domain.models.Episode
import com.sevenzeroes.trekkieapp.core.domain.models.TmdbResponse
import com.sevenzeroes.trekkieapp.core.domain.models.StapiResponse
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter

object Utils {

    fun parseDateFormat(dateString: String?): String? {
        if (Build.VERSION.SDK_INT >= 26) {
            if (dateString!=null){
            val parsedDate = LocalDate.parse(dateString, DateTimeFormatter.ISO_DATE)
                var formatter = DateTimeFormatter.ofPattern("d MMM uuuu")
                return formatter.format(parsedDate)
            }
            return "n/a"

        } else {
            val parser = SimpleDateFormat("yyyy-MM-dd")
            val formatter = SimpleDateFormat("d MMM uuuu")
            val formattedDate = formatter.format(parser.parse(dateString))
            return formattedDate
        }
    }
}