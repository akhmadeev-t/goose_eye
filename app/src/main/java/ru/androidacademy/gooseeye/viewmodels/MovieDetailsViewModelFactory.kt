package ru.androidacademy.gooseeye.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.androidacademy.gooseeye.data.Movie

@Suppress("UNCHECKED_CAST")
class MovieDetailsViewModelFactory(private val movie: Movie) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        MovieDetailsViewModel(movie) as T
}