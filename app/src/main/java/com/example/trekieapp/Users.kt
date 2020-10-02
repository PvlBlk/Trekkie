package com.example.trekieapp


import com.google.gson.annotations.SerializedName

data class Users(
/*    @SerializedName("count")
    var count: Int?,*/
    @SerializedName("episodes")
    var items: List<Item?>?
) {
    data class Item(
        @SerializedName("uid")
        var firstName: String?,
        @SerializedName("title")
        var lastName: String?
    )
}