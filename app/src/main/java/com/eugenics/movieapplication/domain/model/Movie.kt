package com.eugenics.movieapplication.domain.model

data class Movie(
    val adult: Boolean,
    val backdrop_path: String?,
    val id: Int,
    val original_title: String,
    val overview: String,
    val popularity: Float,
    val poster_path: String?,
    val release_date: String,
    val title: String,
    val vote_average: Float,
    val vote_count: Int,
    val status: String?,
    val homepage: String?,
    val budget: Int?,
    val runtime: Int?
)
