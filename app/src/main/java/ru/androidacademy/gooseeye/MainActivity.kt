package ru.androidacademy.gooseeye

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import ru.androidacademy.gooseeye.data.models.Movie
import ru.androidacademy.gooseeye.databinding.ActivityMainBinding
import ru.androidacademy.gooseeye.ui.moviedetails.FragmentMovieDetails
import ru.androidacademy.gooseeye.ui.movielist.FragmentMoviesList

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .apply {
                    add(R.id.fragment_container, FragmentMoviesList.newInstance())
                    commit()
                }
        }
    }

    fun showMovieDetails(movie: Movie) {
        supportFragmentManager.beginTransaction().apply {
            add(R.id.fragment_container, FragmentMovieDetails.newInstance(movie))
            addToBackStack(null)
            setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            commit()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}