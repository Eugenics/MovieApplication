package com.eugenics.movieapplication.data.datasources.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.eugenics.movieapplication.data.api.TmdbApi
import com.eugenics.movieapplication.data.datasources.pagingsource.PagingDataSource
import com.eugenics.movieapplication.domain.core.RemoteDataSource
import com.eugenics.movieapplication.domain.model.Movie
import com.eugenics.movieapplication.domain.util.PAGE_SIZE
import kotlinx.coroutines.flow.Flow

class RemoteDataSourceImpl(private val api: TmdbApi) : RemoteDataSource {
    override fun getMoviesPage(): Flow<PagingData<Movie>> =
        Pager(
            config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
            pagingSourceFactory =
            { PagingDataSource(api = api) }
        ).flow
}