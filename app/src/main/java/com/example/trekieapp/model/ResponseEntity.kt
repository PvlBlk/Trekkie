package com.example.trekieapp.model

import com.google.gson.annotations.SerializedName

class ResponseEntity {

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
}