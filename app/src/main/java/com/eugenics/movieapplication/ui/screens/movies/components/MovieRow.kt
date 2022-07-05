package com.eugenics.movieapplication.ui.screens.movies.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.eugenics.movieapplication.domain.model.Movie
import com.eugenics.movieapplication.domain.util.BACKDROP_SIZE
import com.eugenics.movieapplication.domain.util.POSTER_SIZE
import com.eugenics.movieapplication.domain.util.POSTER_URL

@Composable
fun MovieRow(
    movie: Movie,
    onRowClick: () -> Unit
) {
    Row(
        modifier = Modifier.padding(8.dp)
            .clickable { onRowClick() },
        horizontalArrangement = Arrangement.aligned(Alignment.CenterHorizontally)
    ) {
        SubcomposeAsyncImage(
            model = POSTER_URL + POSTER_SIZE["big"] + movie.poster_path,
            contentDescription = null,
            contentScale = ContentScale.Fit,
            loading = {
                CircularProgressIndicator()
            },
            modifier = Modifier.fillMaxWidth()
        )
//        Text(text = movie.title, style = MaterialTheme.typography.body1)
    }
}