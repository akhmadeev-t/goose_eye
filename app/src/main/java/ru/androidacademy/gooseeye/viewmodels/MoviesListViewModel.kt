package ru.androidacademy.gooseeye.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.androidacademy.gooseeye.data.Movie
import ru.androidacademy.gooseeye.data.Repository

class MoviesListViewModel(private val repository: Repository) : ViewModel() {

    private val _movies = MutableLiveData<List<Movie>>(emptyList())
    val movies get() = _movies

    init {
        getMovies()
    }

    private fun getMovies() {
        viewModelScope.launch {
            val jsonMovies = repository.loadMovies()
            _movies.value = jsonMovies
        }
    }
}