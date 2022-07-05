package com.eugenics.movieapplication.data.repository

import com.eugenics.movieapplication.data.model.MoviesResponse
import com.eugenics.movieapplication.domain.core.RemoteDataSource
import com.eugenics.movieapplication.domain.core.Repository
import com.eugenics.movieapplication.domain.util.Response
import kotlinx.coroutines.flow.Flow

class RepositoryImpl(private val remote: RemoteDataSource) : Repository {
    override suspend fun getMoviesRemote(page: Int): Flow<Response<MoviesResponse>> =
        remote.getMovies(page = page)
}