package com.eugenics.movieapplication.navigation

sealed class Screens(val route: String) {
    object Movies : Screens(route = "movies")
}
