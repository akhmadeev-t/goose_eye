package ru.androidacademy.gooseeye.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.androidacademy.gooseeye.MainActivity
import ru.androidacademy.gooseeye.R
import ru.androidacademy.gooseeye.adapters.ArtistRecyclerAdapter
import ru.androidacademy.gooseeye.adapters.ClickListener
import ru.androidacademy.gooseeye.adapters.MovieRecyclerAdapter
import ru.androidacademy.gooseeye.models.ArtistInfo
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
        layoutManager.orientation = GridLayoutManager.VERTICAL
        recyclerView?.layoutManager = layoutManager
        recyclerView?.adapter = MovieRecyclerAdapter(fillList(), clickListener)
    }

    private fun fillList(): List<MovieInfo> {
        return mutableListOf(
            MovieInfo(moviePoster = R.drawable.item_poster_avengers,
                age = getString(R.string.pg_text),
                tag = getString(R.string.tag_line_text),
                reviews = getString(R.string.review_text),
                movieName = getString(R.string.item_name_avengers),
                duration = getString(R.string.item_min_avengers),
                rating = 4F,
                like = false)
        )
    }

    private val clickListener = object : ClickListener {
        override fun onClick(movieInfo: MovieInfo) {
            val fragmentMoviesDetails = FragmentMoviesDetails()
            activity?.supportFragmentManager?.beginTransaction()?.apply {
                add(R.id.fl_movie_details, fragmentMoviesDetails)
                addToBackStack(null)
                setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                commit()
            }
        }
    }
}
