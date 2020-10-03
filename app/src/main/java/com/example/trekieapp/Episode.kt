package com.example.trekieapp

import com.google.gson.annotations.SerializedName

data class Episode(
/*    @SerializedName("count")
    var count: Int?,*/
    @SerializedName("episodes")
    var episodes: List<Episodes?>?
) {
    data class Episodes(
        @SerializedName("uid")
        var uid: String?,
        @SerializedName("title")
        var title: String?
    )
}