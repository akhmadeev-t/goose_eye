package ru.androidacademy.gooseeye.api.responses

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.androidacademy.gooseeye.data.Genre

@ExperimentalSerializationApi
@Serializable
data class GenresResponse(
    @SerialName("genres")
    val genres: List<Genre>
)