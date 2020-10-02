package com.example.trekieapp

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ResponseWrapper<T> : Serializable {
    @SerializedName("episodes")
    val data: T? = null
    @SerializedName("error")
    val error: Error? = null
}