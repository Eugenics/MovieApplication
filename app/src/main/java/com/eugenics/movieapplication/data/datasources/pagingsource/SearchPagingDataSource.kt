package com.eugenics.movieapplication.data.datasources.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.eugenics.movieapplication.BuildConfig
import com.eugenics.movieapplication.data.api.TmdbApi
import com.eugenics.movieapplication.domain.model.Movie
import com.eugenics.movieapplication.domain.model.convertToModel
import javax.inject.Inject

class SearchPagingDataSource @Inject constructor(
    private val api: TmdbApi,
    private val queryString: String
) : PagingSource<Int, Movie>() {
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? =
        state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val nextPageNumber = params.key ?: 1
        val prevPageNumber = if (nextPageNumber == 1) null else nextPageNumber - 1

        return try {
            val response = api.searchMovie(
                queryString = queryString,
                apiKey = BuildConfig.API_KEY,
                page = nextPageNumber
            )
            val movieList = response.results.map { it.convertToModel() }
            LoadResult.Page(
                data = movieList,
                nextKey = nextPageNumber + 1,
                prevKey = prevPageNumber
            )
        } catch (ex: Exception) {
            LoadResult.Error(throwable = ex)
        }
    }
}