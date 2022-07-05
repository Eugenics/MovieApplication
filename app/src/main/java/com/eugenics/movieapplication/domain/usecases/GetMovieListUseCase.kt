package com.eugenics.movieapplication.domain.usecases

import com.eugenics.movieapplication.data.model.convertToModel
import com.eugenics.movieapplication.domain.core.Repository
import com.eugenics.movieapplication.domain.model.Movie
import com.eugenics.movieapplication.domain.util.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMovieListUseCase @Inject constructor(private val repository: Repository) {
    private suspend fun getMoviesList(page: Int = 1): Flow<Response<List<Movie>>> =
        flow {
            repository.getMoviesRemote(page = page).collect { response ->
                when (response) {
                    is Response.Success -> emit(
                        Response.Success(
                            data = response.data?.results?.map {
                                it.convertToModel()
                            })
                    )
                    is Response.Error -> Response.Error<List<Movie>>(
                        message = response.message ?: ""
                    )
                    else -> emit(Response.Loading())
                }
            }
        }

    suspend operator fun invoke(page: Int): Flow<Response<List<Movie>>> = getMoviesList(page = page)
}