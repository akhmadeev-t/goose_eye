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
import ru.androidacademy.gooseeye.databinding.FragmentMoviesDetailsBinding
import ru.androidacademy.gooseeye.databinding.FragmentMoviesListBinding

class FragmentMoviesList : Fragment() {

    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    lateinit var movieAdapter: MovieRecyclerAdapter
    private var _binding: FragmentMoviesListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerViewMovie()
    }

    override fun onStart() {
        super.onStart()
        fillList()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        coroutineScope.cancel()
        _binding = null
    }

    private fun setUpRecyclerViewMovie() {
        val layoutManager = GridLayoutManager(
            requireActivity(),
            if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) 4 else 2
        )
        movieAdapter = MovieRecyclerAdapter { (activity as MainActivity).showMovieDetails(it) }
        binding.rvMoviesList.apply {
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