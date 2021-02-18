package ru.androidacademy.gooseeye.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

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