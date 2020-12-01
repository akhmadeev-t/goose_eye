package ru.androidacademy.gooseeye.models

data class MovieInfo(var moviePoster: Int,
                     var age: String,
                     var tag: String,
                     var reviews: String,
                     var movieName: String,
                     var duration: String,
                     var rating: Float,
                     var like: Boolean)
