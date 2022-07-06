package com.eugenics.movieapplication.domain.core

import androidx.paging.PagingData
import com.eugenics.movieapplication.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun getPagingMoviesRemote(): Flow<PagingData<Movie>>
}