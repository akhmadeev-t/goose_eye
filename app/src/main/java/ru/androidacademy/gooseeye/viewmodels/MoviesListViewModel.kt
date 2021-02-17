package ru.androidacademy.gooseeye.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.serialization.ExperimentalSerializationApi
import ru.androidacademy.gooseeye.data.Movie
import ru.androidacademy.gooseeye.data.Repository

@ExperimentalSerializationApi
class MoviesListViewModel(private val repository: Repository) : ViewModel() {

    private val _movies = MutableLiveData<List<Movie>>(emptyList())
    val movies get() = _movies

    init {
        loadMovies()
    }

    private fun loadMovies() {
        viewModelScope.launch {
            val jsonMovies = repository.loadMoviesFromApi()
            _movies.value = jsonMovies
        }
    }
}