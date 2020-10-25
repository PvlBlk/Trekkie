package com.example.trekkieapp.model

/**
 * This is from Stapi
 */

data class ResponseEntity(
    val episodes: List<Episode>,
    val page: Page,
    val sort: Sort
)

data class Episode(
    val episodeNumber: Int,
    val featureLength: Boolean,
    val finalScriptDate: Any,
    val productionSerialNumber: String,
    val season: Season,
    val seasonNumber: Int,
    val series: Series,
    val stardateFrom: Double,
    val stardateTo: Double,
    val title: String,
    val titleGerman: String,
    val titleItalian: String,
    val titleJapanese: String,
    val uid: String,
    val usAirDate: String,
    val yearFrom: Int,
    val yearTo: Int
)

data class Page(
    val firstPage: Boolean,
    val lastPage: Boolean,
    val numberOfElements: Int,
    val pageNumber: Int,
    val pageSize: Int,
    val totalElements: Int,
    val totalPages: Int
)

data class Sort(
    val clauses: List<Any>
)

data class Season(
    val title: String,
    val uid: String
)

data class Series(
    val title: String,
    val uid: String
)