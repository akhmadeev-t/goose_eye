package ru.androidacademy.gooseeye.api

import kotlinx.serialization.ExperimentalSerializationApi
import retrofit2.http.GET
import retrofit2.http.Path
import ru.androidacademy.gooseeye.api.responses.ActorsResponse
import ru.androidacademy.gooseeye.api.responses.GenresResponse
import ru.androidacademy.gooseeye.api.responses.MoviesResponse

@ExperimentalSerializationApi
interface MoviesApi {
    @GET("movie/popular")
    suspend fun getPopularMovies(): MoviesResponse

    @GET("genre/movie/list")
    suspend fun getGenres(): GenresResponse

    @GET("movie/{movie_id}/credits")
    suspend fun getActors(@Path("movie_id") movieId: Int): ActorsResponse
}