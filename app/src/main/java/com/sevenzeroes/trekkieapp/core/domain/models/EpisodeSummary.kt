package com.sevenzeroes.trekkieapp.core.domain.models

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

/**
 * Summary of both API's
 */
@Entity
data class EpisodeSummary (
    val air_date: String? = "",
    @PrimaryKey
    val name: String= "",
    val overview: String?= "",
    val season_number: Int?= null,
    val episode_number: Int?= null,
    val vote_average: Double?= null,
    val stardateFrom: Double? = null,
    val stardateTo: Double? = null,
    val still_path: String? = "",
    var expanded: Boolean? = false
    )
