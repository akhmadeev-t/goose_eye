package ru.androidacademy.gooseeye.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.androidacademy.gooseeye.data.Repository

@Suppress("UNCHECKED_CAST")
class MoviesListViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        MoviesListViewModel(Repository()) as T
}