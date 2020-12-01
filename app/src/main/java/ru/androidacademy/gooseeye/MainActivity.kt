package ru.androidacademy.gooseeye

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
                    add(R.id.fl_movie_details, fragmentMoviesList)
                    commit()
                }
        }
    }

}