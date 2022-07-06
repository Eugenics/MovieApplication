package com.eugenics.movieapplication.data.api

import com.eugenics.movieapplication.domain.model.MoviesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface TmdbApi {

    @GET("discover/movie")
    suspend fun getMoviesList(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int = 1
    ): MoviesResponse

    @GET("search/movie")
    suspend fun searchMovie(
        @Query("query") queryString: String,
        @Query("api_key") apiKey: String,
        @Query("page") page: Int = 1
    ): MoviesResponse
}