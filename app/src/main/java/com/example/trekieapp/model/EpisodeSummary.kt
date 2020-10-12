package com.example.trekieapp.model

import java.util.ArrayList

/**
 * Summary of both API's that is used for UI
 */
data class EpisodeSummary (
    val air_date: String,
    val name: String,
    val overview: String,
    val season_number: Int,
    val episode_number: Int,
    val vote_average: Double,
    val stardateFrom: Double,
    val stardateTo: Double,
    val still_path: String

    )
{
}