package com.eugenics.movieapplication.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eugenics.movieapplication.domain.model.Movie
import com.eugenics.movieapplication.domain.usecases.UseCases
import com.eugenics.movieapplication.domain.util.Response
import com.eugenics.movieapplication.domain.util.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val useCases: UseCases) : ViewModel() {

    private val _movies: MutableStateFlow<List<Movie>> = MutableStateFlow(listOf())
    val movies: StateFlow<List<Movie>> = _movies

    private val _message: MutableStateFlow<String> = MutableStateFlow("")
    val message: StateFlow<String> = _message

    private val _status: MutableStateFlow<Status> = MutableStateFlow(Status.LOADING)
    val status: StateFlow<Status> = _status

    private val movie = mutableListOf<Movie>()

    init {
        getMovies()
    }

    private fun getMovies(page: Int = 1) {
        viewModelScope.launch(Dispatchers.IO) {
            useCases.getMovieListUseCase.invoke(page = 1).collect { response ->
                when (response) {
                    is Response.Success -> {
                        _movies.value = response.data ?: listOf()
                        _status.value = Status.SUCCESS
                    }
                    is Response.Error -> {
                        _message.value = response.message ?: ""
                        _status.value = Status.ERROR
                        Timber.e(response.message.toString())
                    }
                    else -> {
                        _status.value = Status.LOADING
                        _message.value = "Loading..."
                    }
                }
            }
        }
    }

    fun setMovie(movie: Movie) {
        this.movie.clear()
        this.movie.add(movie)
    }

    fun getMovie(): Movie = movie.first()
}