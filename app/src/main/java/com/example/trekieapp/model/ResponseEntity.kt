package com.example.trekieapp.model

import com.google.gson.annotations.SerializedName

class ResponseEntity {

    data class Episode(
        @SerializedName("uid")
        var uid: String?,
        @SerializedName("title")
        var title: String?
    )
}