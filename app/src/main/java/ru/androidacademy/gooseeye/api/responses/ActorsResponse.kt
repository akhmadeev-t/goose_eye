package ru.androidacademy.gooseeye.api.responses

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.androidacademy.gooseeye.data.models.Actor

@ExperimentalSerializationApi
@Serializable
data class ActorsResponse(
    @SerialName("id")
    val id: Int,
    @SerialName("cast")
    val cast: List<Actor>
)
