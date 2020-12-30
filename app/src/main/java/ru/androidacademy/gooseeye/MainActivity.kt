package ru.androidacademy.gooseeye

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import ru.androidacademy.gooseeye.fragments.FragmentMoviesDetails
import ru.androidacademy.gooseeye.fragments.FragmentMoviesList
import ru.androidacademy.gooseeye.models.MovieInfo

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentMoviesList = FragmentMoviesList()
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .apply {
                    add(R.id.fragment_container, fragmentMoviesList)
                    commit()
                }
        }
    }

    fun showMovieDetails(movieInfo: MovieInfo) {
        val fragmentMoviesDetails = FragmentMoviesDetails()
        supportFragmentManager.beginTransaction().apply {
            add(R.id.fragment_container, fragmentMoviesDetails)
            addToBackStack(null)
            setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            commit()
        }
    }

}