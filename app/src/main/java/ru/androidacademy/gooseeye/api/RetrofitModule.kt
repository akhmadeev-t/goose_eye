package ru.androidacademy.gooseeye.api

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

object RetrofitModule {
    private val client = OkHttpClient().newBuilder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .addInterceptor(ApiKeyInterceptor())
        .build()

    private val jsonFormat = Json { ignoreUnknownKeys = true }

    @ExperimentalSerializationApi
    private val retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl(NetworkModule.getBaseUrl())
        .addConverterFactory(jsonFormat.asConverterFactory("application/json".toMediaType()))
        .build()

    @ExperimentalSerializationApi
    val moviesApi = retrofit.create(MoviesApi::class.java)
}