package com.eugenics.movieapplication.data.api

import com.eugenics.movieapplication.data.model.MoviesResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface TmdbApi {

    @GET("discover/movie")
    fun getMoviesList(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int = 1
    ): Deferred<MoviesResponse>
}