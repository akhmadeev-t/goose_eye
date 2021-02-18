package ru.androidacademy.gooseeye.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.androidacademy.gooseeye.api.NetworkModule
import ru.androidacademy.gooseeye.api.RetrofitModule

@ExperimentalSerializationApi
class Repository() {

    @Serializable
    class JsonMovie(
        @SerialName("id")
        val id: Int,
        @SerialName("title")
        val title: String,
        @SerialName("poster_path")
        val poster: String,
        @SerialName("backdrop_path")
        val backdrop: String,
        @SerialName("genre_ids")
        val genreIds: List<Int>,
        @SerialName("vote_average")
        val ratings: Float,
        @SerialName("overview")
        val overview: String,
        @SerialName("adult")
        val adult: Boolean,
        @SerialName("vote_count")
        val numberOfReviews: Int,
    )

    private suspend fun loadGenresFromApi(): List<Genre> = withContext(Dispatchers.IO) {
        val jsonGenres = mutableListOf<Genre>()
        coroutineScope {
            jsonGenres.addAll(RetrofitModule.moviesApi.getGenres().genres)
        }
        jsonGenres
    }

    internal suspend fun loadCastFromApi(movieId: Int): List<Actor> = withContext(Dispatchers.IO) {
        val jsonActors = mutableListOf<Actor>()
        coroutineScope {
            jsonActors.addAll(RetrofitModule.moviesApi.getActors(movieId).cast)
        }
        getCastWithProfile(jsonActors)
    }

    private fun getCastWithProfile(
        jsonActors: List<Actor>
    ): List<Actor> {
        val actors = mutableListOf<Actor>()
        actors.addAll(jsonActors)
        jsonActors.forEach { jsonActor ->
            if (jsonActor.picture == null) {
                actors.remove(jsonActor)
            }
        }
        actors.forEach { actor ->
            actor.picture = NetworkModule.createImageUrl(actor.picture!!)
        }
        return actors
    }

    internal suspend fun loadMoviesFromApi(): List<Movie> = withContext(Dispatchers.IO) {
        val jsonMovies = mutableListOf<JsonMovie>()
        val genres = loadGenresFromApi()
        coroutineScope {
            jsonMovies.addAll(RetrofitModule.moviesApi.getPopularMovies().results)
        }
        parseMoviesFromApi(jsonMovies, genres)
    }

    private fun parseMoviesFromApi(
        jsonMovies: List<JsonMovie>,
        genres: List<Genre>
    ): List<Movie> {
        val genresMap = genres.associateBy { it.id }

        return jsonMovies.map { jsonMovie ->
            (Movie(
                id = jsonMovie.id,
                title = jsonMovie.title,
                overview = jsonMovie.overview,
                poster = NetworkModule.createImageUrl(jsonMovie.poster),
                backdrop = NetworkModule.createImageUrl(jsonMovie.backdrop),
                ratings = jsonMovie.ratings,
                numberOfRatings = jsonMovie.numberOfReviews,
                minimumAge = if (jsonMovie.adult) 16 else 13,
                runtime = 0,
                genres = jsonMovie.genreIds.map {
                    genresMap[it] ?: throw IllegalArgumentException("Genre not found")
                },
                actors = emptyList()
            ))
        }
    }
}