package com.eugenics.movieapplication.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.paging.compose.collectAsLazyPagingItems
import com.eugenics.movieapplication.ui.screens.movie.MovieScreen
import com.eugenics.movieapplication.ui.screens.movies.MoviesScreen
import com.eugenics.movieapplication.ui.viewmodels.MainViewModel

@Composable
fun NavGraph(navController: NavHostController) {

    val viewModel: MainViewModel = viewModel()

    NavHost(
        startDestination = Screens.Movies.route,
        navController = navController
    ) {
        composable(route = Screens.Movies.route) {
            MoviesScreen(
                navController = navController,
                moviesPaging = viewModel.pageMovies.collectAsLazyPagingItems(),
                status = viewModel.status.collectAsState().value,
                message = viewModel.message.collectAsState().value,
                onMovieClick = { viewModel.setMovie(movie = it) },
                onSearchClicked = { viewModel.searchMovie(queryString = it) },
                reloadMovieHandler = { viewModel.getPagingMovies() }
            )
        }
        composable(route = Screens.Movie.route) {
            MovieScreen(
                navController = navController,
                movie = viewModel.getMovie()
            )
        }
    }
}