package ru.androidacademy.gooseeye.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import ru.androidacademy.gooseeye.MainActivity
import ru.androidacademy.gooseeye.R
import ru.androidacademy.gooseeye.adapters.ArtistRecyclerAdapter
import ru.androidacademy.gooseeye.data.models.Movie
import ru.androidacademy.gooseeye.databinding.FragmentMoviesDetailsBinding
import ru.androidacademy.gooseeye.viewmodels.MovieDetailsViewModel
import ru.androidacademy.gooseeye.viewmodels.MovieDetailsViewModelFactory

class FragmentMovieDetails : Fragment() {

    private lateinit var movie: Movie
    private lateinit var actorAdapter: ArtistRecyclerAdapter
    private var _binding: FragmentMoviesDetailsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MovieDetailsViewModel by viewModels { MovieDetailsViewModelFactory(movie) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movie = arguments?.getParcelable(MOVIE)!!
        (activity as MainActivity).let {
            it.setSupportActionBar(binding.toolbar)
            it.supportActionBar?.apply {
                setDisplayHomeAsUpEnabled(true)
                setDisplayShowHomeEnabled(true)
            }
        }
        actorAdapter = ArtistRecyclerAdapter()
        subscribe(view)
        setUpRecyclerViewArtist()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setUpRecyclerViewArtist() {
        val linearLayoutManager = LinearLayoutManager(requireActivity())
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        binding.apply {
            val divider =
                DividerItemDecoration(rvArtistList.context, linearLayoutManager.orientation)
            divider.setDrawable(
                ContextCompat.getDrawable(
                    rvArtistList.context!!,
                    R.drawable.divider_separator
                )!!
            )
            rvArtistList.apply {
                addItemDecoration(divider)
                layoutManager = linearLayoutManager
                adapter = actorAdapter
            }
        }
    }

    private fun subscribe(view: View) {
        viewModel.movieDetails.observe(viewLifecycleOwner) { movie ->
            binding.apply {
                Glide
                    .with(view)
                    .load(movie.backdrop)
                    .into(imgPoster)
                collapsingToolbar.title = movie.title
                tvPg.text = "${movie.minimumAge}${view.resources.getString(R.string.pg_text)}"
                tvTagLine.text = movie.genres.joinToString { it.name }
                ratingBar.rating = (movie.ratings) / 2
                tvReview.text = "${movie.numberOfRatings} ${
                    view.resources.getQuantityString(
                        R.plurals.review_plurals,
                        movie.numberOfRatings
                    )
                }"
                tvStoryLineDetails.text = movie.overview
            }
        }

        viewModel.actors.observe(viewLifecycleOwner) {
            actorAdapter.setActors(it)
            Log.d("ActorsList", it.toString())
        }
    }

    companion object {

        private const val MOVIE = "movie"

        fun newInstance(movie: Movie) = FragmentMovieDetails().apply {
            arguments = Bundle().apply {
                putParcelable(MOVIE, movie)
            }
        }
    }
}