package ru.androidacademy.gooseeye.ui.moviedetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.androidacademy.gooseeye.data.models.Movie
import ru.androidacademy.gooseeye.data.Repository

@Suppress("UNCHECKED_CAST")
class MovieDetailsViewModelFactory(private val movie: Movie) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        MovieDetailsViewModel(movie, Repository()) as T
}