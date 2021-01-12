package ru.androidacademy.gooseeye

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import ru.androidacademy.gooseeye.data.Movie
import ru.androidacademy.gooseeye.fragments.FragmentMoviesDetails
import ru.androidacademy.gooseeye.fragments.FragmentMoviesList

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

    fun showMovieDetails(movie: Movie) {
        supportFragmentManager.beginTransaction().apply {
            add(R.id.fragment_container, FragmentMoviesDetails.newInstance(movie))
            addToBackStack(null)
            setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            commit()
        }
    }

}