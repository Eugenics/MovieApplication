package com.eugenics.movieapplication.di

import com.eugenics.movieapplication.data.api.TmdbApi
import com.eugenics.movieapplication.data.datasources.remote.RemoteDataSourceImpl
import com.eugenics.movieapplication.data.repository.RepositoryImpl
import com.eugenics.movieapplication.domain.core.RemoteDataSource
import com.eugenics.movieapplication.domain.core.Repository
import com.eugenics.movieapplication.domain.usecases.GetMovieListUseCase
import com.eugenics.movieapplication.domain.usecases.UseCases
import com.eugenics.movieapplication.domain.util.MAIN_URL
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideGson(): Gson =
        GsonBuilder()
            .setPrettyPrinting()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()

    @Singleton
    @Provides
    fun provideTmdbApi(gson: Gson): TmdbApi =
        Retrofit.Builder()
            .baseUrl(MAIN_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
            .create(TmdbApi::class.java)

    @Singleton
    @Provides
    fun provideRemoteDataSource(api: TmdbApi): RemoteDataSource = RemoteDataSourceImpl(api = api)

    @Singleton
    @Provides
    fun provideRepository(remote: RemoteDataSource): Repository = RepositoryImpl(remote = remote)

    @Singleton
    @Provides
    fun provideUseCases(repository: Repository): UseCases =
        UseCases(getMovieListUseCase = GetMovieListUseCase(repository = repository))
}