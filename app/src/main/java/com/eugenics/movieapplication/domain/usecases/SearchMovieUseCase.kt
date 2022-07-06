package com.eugenics.movieapplication.domain.usecases

import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.eugenics.movieapplication.domain.core.Repository
import com.eugenics.movieapplication.domain.model.Movie
import com.eugenics.movieapplication.domain.util.Response
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SearchMovieUseCase(
    private val repository: Repository
) {
    operator fun invoke(
        queryString: String,
        scope: CoroutineScope
    ): Flow<Response<PagingData<Movie>>> =
        flow {
            emit(Response.Loading())
            try {
                repository.getPagingSearchMovieRemote(queryString = queryString)
                    .cachedIn(scope = scope).collect {
                        emit(Response.Success(data = it))
                    }
            } catch (ex: Exception) {
                emit(Response.Error(message = ex.message.toString()))
            }
        }
}