package com.eugenics.movieapplication.ui.screens.movie

import android.widget.ImageButton
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.SubcomposeAsyncImage
import com.eugenics.movieapplication.domain.model.Movie
import com.eugenics.movieapplication.domain.util.BACKDROP_SIZE
import com.eugenics.movieapplication.domain.util.IMAGE_URL
import com.eugenics.movieapplication.domain.util.POSTER_SIZE
import com.eugenics.movieapplication.domain.util.POSTER_URL

@Composable
fun MovieScreen(
    navController: NavHostController,
    movie: Movie
) {
    val scrollableState = rememberScrollState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = movie.title,
                        style = MaterialTheme.typography.subtitle1,
                        modifier = Modifier.padding(5.dp)
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(imageVector = Icons.Filled.Close, contentDescription = null)
                    }
                },
                elevation = AppBarDefaults.TopAppBarElevation
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues = paddingValues)
                .verticalScroll(state = scrollableState)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxWidth()
                    .padding(10.dp)
            ) {
                SubcomposeAsyncImage(
                    model = IMAGE_URL + BACKDROP_SIZE["large"] + movie.backdrop_path,
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth,
                    loading = {
                        CircularProgressIndicator(
                            modifier = Modifier.align(alignment = Alignment.Center)
                        )
                    }
                )
            }
            Text(
                text = movie.overview,
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}