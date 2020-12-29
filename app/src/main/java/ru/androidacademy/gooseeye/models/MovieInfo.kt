package ru.androidacademy.gooseeye.models

data class MovieInfo(val moviePoster: Int,
                     val age: String,
                     val tag: String,
                     val reviews: String,
                     val movieName: String,
                     val duration: String,
                     val rating: Float,
                     val like: Boolean)
