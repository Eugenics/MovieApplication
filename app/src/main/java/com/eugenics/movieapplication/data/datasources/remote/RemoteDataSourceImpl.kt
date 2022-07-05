package com.eugenics.movieapplication.data.datasources.remote

import com.eugenics.movieapplication.BuildConfig
import com.eugenics.movieapplication.data.api.TmdbApi
import com.eugenics.movieapplication.data.model.MoviesResponse
import com.eugenics.movieapplication.domain.core.RemoteDataSource
import com.eugenics.movieapplication.domain.util.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RemoteDataSourceImpl(private val api: TmdbApi) : RemoteDataSource {
    override suspend fun getMovies(page: Int): Flow<Response<MoviesResponse>> =
        flow {
            emit(Response.Loading())
            try {
                emit(
                    Response.Success(
                        data = api.getMoviesList(apiKey = BuildConfig.API_KEY, page = page)
                            .await()
                    )
                )
            } catch (ex: Exception) {
                emit(
                    Response.Error(message = ex.message.toString())
                )
            }
        }

}