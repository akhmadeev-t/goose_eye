package ru.androidacademy.gooseeye.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.androidacademy.gooseeye.data.Actor
import ru.androidacademy.gooseeye.data.Movie
import ru.androidacademy.gooseeye.data.Repository

class MovieDetailsViewModel(
    private val movie: Movie,
    private val repository: Repository
    ) : ViewModel() {

    private val _movieDetails = MutableLiveData<Movie>()
    val movieDetails get() = _movieDetails
    private val _actors = MutableLiveData<List<Actor>>()
    val actors get() = _actors

    init {
        loadMovie()
        loadActors()
    }

    private fun loadMovie() {
        _movieDetails.value = movie
    }

    private fun loadActors() {
        viewModelScope.launch {
            val cast = repository.loadCastFromApi(movie.id)
            _actors.value = cast
        }
    }
}