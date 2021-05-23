package com.sevenzeroes.trekkieapp.core.helpers

import android.os.Build
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter

object TimeUtils {

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
            return formatter.format(parser.parse(dateString))
        }
    }
}