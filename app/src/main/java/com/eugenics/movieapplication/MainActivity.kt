package com.eugenics.movieapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.eugenics.movieapplication.navigation.NavGraph
import com.eugenics.movieapplication.ui.theme.MovieApplicationTheme
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Timber.plant(Timber.DebugTree())
        Timber.i("Start timber...")

        setContent {
            val navController = rememberNavController()

            MovieApplicationTheme(darkTheme = true) {
                NavGraph(
                    navController = navController
                )
            }
        }
    }
}