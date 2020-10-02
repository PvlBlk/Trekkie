package com.example.trekieapp

import com.google.gson.annotations.SerializedName

data class Seasons(
/*    @SerializedName("count")
    var count: Int?,*/
    @SerializedName("seasons")
    var seasons: List<Season?>?
) {
    data class Season(
        @SerializedName("title")
        var title: String?,
        @SerializedName("seasonNumber")
        var seasonNumber: String?
    )
}