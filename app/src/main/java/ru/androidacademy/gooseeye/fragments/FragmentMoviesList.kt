package ru.androidacademy.gooseeye.fragments

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.androidacademy.gooseeye.MainActivity
import ru.androidacademy.gooseeye.R
import ru.androidacademy.gooseeye.adapters.MovieRecyclerAdapter
import ru.androidacademy.gooseeye.models.MovieInfo

class FragmentMoviesList : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_movies_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerViewMovie()
    }

    private fun setUpRecyclerViewMovie() {
        val recyclerView = view?.findViewById<RecyclerView>(R.id.rv_movies_list)
        val layoutManager = GridLayoutManager(requireActivity(), 2)
        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            layoutManager.spanCount = 4
        }
        layoutManager.orientation = GridLayoutManager.VERTICAL
        val movieAdapter = MovieRecyclerAdapter { (activity as MainActivity).showMovieDetails(it) }
        movieAdapter.setMovies(fillList())
        recyclerView?.apply {
            this.layoutManager = layoutManager
            this.adapter = movieAdapter
        }
    }

    private fun fillList(): List<MovieInfo> {
        return listOf(
            MovieInfo(
                moviePoster = R.drawable.item_poster_avengers,
                age = "13+",
                tag = "Action, Adventure, Drama",
                reviews = "125 REVIEWS",
                movieName = "Avengers: End Game",
                duration = "137 MIN",
                rating = 4F,
                like = false
            ),
            MovieInfo(
                R.drawable.item_poster_tenet,
                "16+",
                "Action, Sci-Fi, Thriller",
                "98 REVIEWS",
                "Tenet",
                "97 MIN",
                5F,
                true
            ),
            MovieInfo(
                R.drawable.item_poster_black_widow,
                "13+",
                "Action, Adventure, Sci-Fi",
                "38 REVIEWS",
                "Black Widow",
                "102 MIN",
                4F,
                false
            ),
            MovieInfo(
                R.drawable.item_poster_wonder_women,
                "13+",
                "Action, Adventure, Fantasy",
                "74 REVIEWS",
                "Wonder Women 1984",
                "74 MIN",
                5F,
                false
            )
        )
    }

}