package ru.androidacademy.gooseeye

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import ru.androidacademy.gooseeye.adapters.ClickListener
import ru.androidacademy.gooseeye.fragments.FragmentMoviesDetails
import ru.androidacademy.gooseeye.fragments.FragmentMoviesList
import ru.androidacademy.gooseeye.models.MovieInfo

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentMoviesList = FragmentMoviesList(clickListener)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .apply {
                    add(R.id.fragment_container, fragmentMoviesList)
                    commit()
                }
        }
    }

    private val clickListener = object : ClickListener {
        override fun onClick(movieInfo: MovieInfo) {
            val fragmentMoviesDetails = FragmentMoviesDetails()
            supportFragmentManager.beginTransaction().apply {
                add(R.id.fragment_container, fragmentMoviesDetails)
                addToBackStack(null)
                setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                commit()
            }
        }
    }

}