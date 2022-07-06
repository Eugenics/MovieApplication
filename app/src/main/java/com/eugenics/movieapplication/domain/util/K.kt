package com.eugenics.movieapplication.domain.util

import androidx.compose.ui.unit.dp

const val MAIN_URL = "https://api.themoviedb.org/3/"
const val IMAGE_URL = "https://image.tmdb.org/t/p/"
const val POSTER_URL = IMAGE_URL
const val BACKDROP_URL = IMAGE_URL

const val PAGE_SIZE = 20
const val MAX_PAGE = 1000

val TOP_APP_BAR_HEIGHT = 56.dp

val POSTER_SIZE = mapOf(
    "tiny" to "w154",
    "small" to "w185",
    "medium" to "w342",
    "big" to "w500",
    "large" to "w780",
    "original" to "original"
)
val BACKDROP_SIZE = mapOf(
    "small" to "w300",
    "medium" to "w780",
    "large" to "w1280",
    "original" to "original"
)