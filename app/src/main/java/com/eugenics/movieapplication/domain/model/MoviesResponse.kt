package com.eugenics.movieapplication.domain.model

import com.google.gson.annotations.SerializedName

data class MoviesResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int,
    @SerializedName("results")
    val results: List<MovieResponse>
)

data class MovieResponse(
    @SerializedName("adult")
    val adult: Boolean,
    @SerializedName("backdrop_path")
    val backdrop_path: String?,
    @SerializedName("id")
    val id: Int,
    @SerializedName("original_title")
    val original_title: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("popularity")
    val popularity: Float,
    @SerializedName("poster_path")
    val poster_path: String?,
    @SerializedName("release_date")
    val release_date: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("vote_average")
    val vote_average: Float,
    @SerializedName("vote_count")
    val vote_count: Int,
    @SerializedName("status")
    val status: String?,
    @SerializedName("homepage")
    val homepage: String?,
    @SerializedName("budget")
    val budget: Int?,
    @SerializedName("runtime")
    val runtime: Int?
)

fun MovieResponse.convertToModel(): Movie =
    Movie(
        adult = this.adult,
        backdrop_path = this.backdrop_path,
        id = this.id,
        original_title = this.original_title,
        overview = this.overview,
        popularity = this.popularity,
        poster_path = this.poster_path,
        release_date = this.release_date,
        title = this.title,
        vote_average = this.vote_average,
        vote_count = this.vote_count,
        status = this.status,
        homepage = this.homepage,
        budget = this.budget,
        runtime = this.runtime
    )