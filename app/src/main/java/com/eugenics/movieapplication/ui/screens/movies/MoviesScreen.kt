package com.eugenics.movieapplication.ui.screens.movies

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.eugenics.movieapplication.domain.model.Movie
import com.eugenics.movieapplication.domain.util.Status
import com.eugenics.movieapplication.ui.screens.movies.components.MovieRow
import kotlinx.coroutines.flow.StateFlow

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MoviesScreen(
    navController: NavHostController,
    moviesResponse: StateFlow<List<Movie>>,
    message: StateFlow<String>,
    status: StateFlow<Status>
) {
    val movies = moviesResponse.collectAsState()
    val state = status.collectAsState()
    val listState = rememberLazyListState()

    Scaffold { paddingValues ->
        LazyVerticalGrid(
            contentPadding = paddingValues,
            state = listState,
            cells = GridCells.Adaptive(minSize = 128.dp)
        ) {
            if (state.value == Status.SUCCESS) {
                items(movies.value) { movie ->
                    MovieRow(movie = movie, onRowClick = {})
                }
            } else {
                item {
                    Text(text = message.value, style = MaterialTheme.typography.h3)
                }
            }
        }
    }
}