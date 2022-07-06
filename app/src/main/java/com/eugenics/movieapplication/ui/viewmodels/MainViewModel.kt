package com.eugenics.movieapplication.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.eugenics.movieapplication.domain.model.Movie
import com.eugenics.movieapplication.domain.usecases.UseCases
import com.eugenics.movieapplication.domain.util.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {

    private val _message: MutableStateFlow<String> = MutableStateFlow("")
    val message: StateFlow<String> = _message

    private val _status: MutableStateFlow<Status> = MutableStateFlow(Status.LOADING)
    val status: StateFlow<Status> = _status

    private val movie = mutableListOf<Movie>()

    private val _movieList: MutableStateFlow<PagingData<Movie>> =
        MutableStateFlow(PagingData.empty())
    val pageMovies: StateFlow<PagingData<Movie>> = _movieList

    init {
        getPagingMovies()
    }

    fun setMovie(movie: Movie) {
        this.movie.clear()
        this.movie.add(movie)
    }

    fun getMovie(): Movie = movie.first()

    private fun getPagingMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            useCases.getMovieListUseCase.invoke(scope = viewModelScope).collect { response ->
                _status.value = response.status ?: Status.LOADING
                when (response.status ?: Status.LOADING) {
                    Status.SUCCESS -> {
                        _movieList.value = response.data ?: PagingData.empty()
                    }
                    Status.ERROR -> _message.value = response.message ?: ""
                    else -> _message.value = "Loading..."
                }
            }
        }
    }
}