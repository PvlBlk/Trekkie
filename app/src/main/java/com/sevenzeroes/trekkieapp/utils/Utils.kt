package com.sevenzeroes.trekkieapp.utils

import android.os.Build
import android.util.Log
import com.sevenzeroes.trekkieapp.model.Episode
import com.sevenzeroes.trekkieapp.model.ResponseEntity
import com.sevenzeroes.trekkieapp.model.SpecificsEntity
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter

object Utils {
    @JvmStatic
    fun parseEpisodeJson(response : JsonElement?) : List<Episode>? {
        response as JsonObject?
        val result = Gson().fromJson(response, ResponseEntity::class.java)
        Log.d("Utils::", "" + result.toString())
        return result.episodes
    }
    @JvmStatic
    fun parseSpecificJson(response : JsonElement?) : SpecificsEntity? {
        response as JsonObject?
        val result : SpecificsEntity? = Gson().fromJson(response, SpecificsEntity::class.java)
        Log.d("Utils::", "" + result.toString())
        return result
    }

    @JvmStatic
    fun parseDateFormat(dateString: String?): String? {
        if (Build.VERSION.SDK_INT >= 26) {
            if (dateString!=null){
            val parsedDate = LocalDate.parse(dateString, DateTimeFormatter.ISO_DATE)
                var formatter = DateTimeFormatter.ofPattern("d MMM uuuu")
                val formattedDate = formatter.format(parsedDate)
                return formattedDate
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