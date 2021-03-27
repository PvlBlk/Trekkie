package com.sevenzeroes.trekkieapp.list.data

/**
 * Summary of both API's that is used for UI
 */
data class EpisodeSummary (
    val air_date: String? = "",
    val name: String?= "",
    val overview: String?= "",
    val season_number: Int?= null,
    val episode_number: Int?= null,
    val vote_average: Double?= null,
    val stardateFrom: Double? = null,
    val stardateTo: Double? = null,
    val still_path: String? = "",
    var expanded: Boolean? = false

    )
{
}