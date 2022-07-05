package com.eugenics.movieapplication.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
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
                moviesResponse = viewModel.movies,
                message = viewModel.message,
                status = viewModel.status
            )
        }
    }
}