package com.eugenics.movieapplication.ui.screens.movies

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.eugenics.movieapplication.domain.model.Movie
import com.eugenics.movieapplication.domain.util.Status
import com.eugenics.movieapplication.navigation.Screens
import com.eugenics.movieapplication.ui.screens.movies.components.MovieRow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun MoviesScreen(
    navController: NavHostController,
    moviesPaging: LazyPagingItems<Movie>,
    onMovieClick: (movie: Movie) -> Unit
) {
    val stateClass = rememberScreenState(lazyListState = rememberLazyListState())
    val listState = stateClass.lazyListState

    Scaffold { paddingValues ->
        LazyColumn(
            contentPadding = paddingValues,
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            state = listState
        ) {
            items(items = moviesPaging,
                key = {
                    it.id
                }) { movie ->
                movie?.let {
                    MovieRow(movie = movie, onRowClick = {
                        onMovieClick(movie)
                        navController.navigate(Screens.Movie.route)
                    })
                }
            }
        }
    }
}

class MovieScreenState(
    val lazyListState: LazyListState
)

@Composable
fun rememberScreenState(lazyListState: LazyListState): MovieScreenState =
    MovieScreenState(lazyListState = lazyListState)