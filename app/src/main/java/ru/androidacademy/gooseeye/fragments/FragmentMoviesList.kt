package ru.androidacademy.gooseeye.fragments

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import ru.androidacademy.gooseeye.MainActivity
import ru.androidacademy.gooseeye.R
import ru.androidacademy.gooseeye.adapters.MovieRecyclerAdapter
import ru.androidacademy.gooseeye.data.loadMovies

class FragmentMoviesList : Fragment() {

    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    lateinit var movieAdapter: MovieRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_movies_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerViewMovie()
    }

    override fun onStart() {
        super.onStart()
        fillList()
    }

    override fun onStop() {
        super.onStop()
        coroutineScope.cancel()
    }

    private fun setUpRecyclerViewMovie() {
        val recyclerView = view?.findViewById<RecyclerView>(R.id.rv_movies_list)
        val layoutManager = GridLayoutManager(
            requireActivity(),
            if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) 4 else 2
        )
        movieAdapter = MovieRecyclerAdapter { (activity as MainActivity).showMovieDetails(it) }
        recyclerView?.apply {
            this.layoutManager = layoutManager
            this.adapter = movieAdapter
        }
    }

    private fun fillList() {
        coroutineScope.launch {
            movieAdapter.setMovies(loadMovies(requireContext()))
        }
    }

    companion object {
        fun newInstance() = FragmentMoviesList()
    }

}