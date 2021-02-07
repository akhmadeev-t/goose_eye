package ru.androidacademy.gooseeye.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.androidacademy.gooseeye.data.Actor
import ru.androidacademy.gooseeye.data.Movie

class MovieDetailsViewModel(private val movie: Movie) : ViewModel() {

    private val _movieDetails = MutableLiveData<Movie>()
    val movieDetails get() = _movieDetails
    private val _actors = MutableLiveData<List<Actor>>()
    val actors get() = _actors

    init {
        getMovie()
    }

    private fun getMovie() {
        _movieDetails.value = movie
        _actors.value = movie.actors
    }
}