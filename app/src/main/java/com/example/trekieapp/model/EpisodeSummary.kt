package com.example.trekieapp.model

data class EpisodeSummary (
    val air_date: String,
    val name: String,
    val overview: String,
    val season_number: Int,
    val episode_number: Int,
    val vote_average: Double

    )