package com.eugenics.movieapplication.domain.core

import com.eugenics.movieapplication.data.model.MoviesResponse
import com.eugenics.movieapplication.domain.util.Response
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource : DataSource {
    suspend fun getMovies(page: Int): Flow<Response<MoviesResponse>>
}