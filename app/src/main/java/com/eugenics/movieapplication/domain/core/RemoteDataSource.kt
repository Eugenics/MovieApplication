package com.eugenics.movieapplication.domain.core

import androidx.paging.PagingData
import com.eugenics.movieapplication.domain.model.Movie
import com.eugenics.movieapplication.domain.model.MoviesResponse
import com.eugenics.movieapplication.domain.util.Response
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource : DataSource {
    fun getMoviesPage(): Flow<PagingData<Movie>>
}