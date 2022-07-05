package com.eugenics.movieapplication.domain.util

sealed class Response<T>(
    val status: Status?,
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T?) : Response<T>(
        status = Status.SUCCESS,
        data = data,
        message = "Success..."
    )

    class Error<T>(message: String) : Response<T>(
        status = Status.ERROR,
        message = message
    )

    class Loading<T>() : Response<T>(
        status = Status.LOADING,
        message = "Loading..."
    )
}
