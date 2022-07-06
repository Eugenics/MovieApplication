package com.eugenics.movieapplication.ui.screens.movies.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.ImeAction
import com.eugenics.movieapplication.domain.util.TOP_APP_BAR_HEIGHT


@Composable
fun TopAppBar(
    data: TopAppBarData
) {
    SearchBar(
        text = data.text,
        onTextChange = data.onTextChange,
        onSearchClicked = data.onSearchClicked,
        onCloseClick = data.onCloseClick
    )
}

@Composable
private fun SearchBar(
    text: String,
    onTextChange: (String) -> Unit,
    onSearchClicked: (String) -> Unit,
    onCloseClick: () -> Unit
) {
    val value = rememberSaveable { mutableStateOf(text) }
    val isInSearch = rememberSaveable { mutableStateOf(false) }

    Surface(
        modifier = Modifier.fillMaxWidth()
            .height(TOP_APP_BAR_HEIGHT),
        elevation = AppBarDefaults.TopAppBarElevation
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = value.value,
                onValueChange = {
                    value.value = it
                    onTextChange(it)
                },
                placeholder = {
                    Text(
                        modifier = Modifier
                            .alpha(alpha = ContentAlpha.medium),
                        text = "Search here...",
                        color = Color.White
                    )
                },
                textStyle = MaterialTheme.typography.subtitle1,
                singleLine = true,
                leadingIcon = {
                    IconButton(
                        modifier = Modifier
                            .alpha(alpha = ContentAlpha.medium),
                        onClick = {
                            onSearchClicked(value.value)
                            isInSearch.value = true
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = null,
                            tint = MaterialTheme.colors.onSurface
                        )
                    }
                },
                trailingIcon = {
                    IconButton(
                        modifier = Modifier
                            .semantics {
                                contentDescription = "CloseButton"
                            },
                        onClick = {
                            value.value = ""
                            if (isInSearch.value) {
                                isInSearch.value = false
                                onCloseClick()
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = null,
                            tint = MaterialTheme.colors.onSurface
                        )
                    }
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Search
                ),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        onSearchClicked(value.value)
                        isInSearch.value = true
                    }
                ),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    cursorColor = MaterialTheme.colors.onSurface
                )
            )
        }
    }
}


data class TopAppBarData(
    val text: String,
    val onTextChange: (String) -> Unit,
    val onSearchClicked: (String) -> Unit,
    val onCloseClick: () -> Unit
)