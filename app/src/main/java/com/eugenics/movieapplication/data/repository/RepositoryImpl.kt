package com.eugenics.movieapplication.data.repository

import androidx.paging.PagingData
import com.eugenics.movieapplication.domain.core.RemoteDataSource
import com.eugenics.movieapplication.domain.core.Repository
import com.eugenics.movieapplication.domain.model.Movie
import kotlinx.coroutines.flow.Flow

class RepositoryImpl(
    private val remote: RemoteDataSource
) : Repository {
    override fun getPagingMoviesRemote(): Flow<PagingData<Movie>> =
        remote.getMoviesPage()

    override fun getPagingSearchMovieRemote(queryString: String): Flow<PagingData<Movie>> =
        remote.searchMovie(queryString = queryString)
}