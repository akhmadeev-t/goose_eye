package ru.androidacademy.gooseeye.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.androidacademy.gooseeye.R
import ru.androidacademy.gooseeye.RatingBarSvg
import ru.androidacademy.gooseeye.adapters.ArtistRecyclerAdapter
import ru.androidacademy.gooseeye.data.Actor
import ru.androidacademy.gooseeye.data.Movie
import ru.androidacademy.gooseeye.databinding.FragmentMoviesDetailsBinding

class FragmentMoviesDetails : Fragment() {

    private lateinit var movie: Movie
    private var _binding: FragmentMoviesDetailsBinding? = null
    private val binding get() = _binding!!

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
        binding.apply {
            Glide
                .with(view)
                .load(movie.backdrop)
                .into(imgPoster)
            tvPg.text = "${movie.minimumAge}${view.resources.getString(R.string.pg_text)}"
            tvName.text = movie.title
            tvTagLine.text = movie.genres.joinToString { it.name }
            ratingBar.rating = (movie.ratings) / 2
            tvReview.text = "${movie.numberOfRatings} ${
                view.resources.getQuantityString(
                    R.plurals.review_plurals,
                    movie.numberOfRatings
                )
            }"
            tvStoryLineDetails.text = movie.overview
            btnBack.setOnClickListener {
                activity?.onBackPressed()
            }
        }
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
            val divider = DividerItemDecoration(rvArtistList.context, linearLayoutManager.orientation)
            divider.setDrawable(
                ContextCompat.getDrawable(
                    rvArtistList.context!!,
                    R.drawable.divider_separator
                )!!
            )
            rvArtistList.apply {
                addItemDecoration(divider)
                layoutManager = linearLayoutManager
                adapter = ArtistRecyclerAdapter(fillList())
            }
        }
    }

    private fun fillList(): List<Actor> {
        return movie.actors
    }

    companion object {

        private const val MOVIE = "movie"

        fun newInstance(movie: Movie) = FragmentMoviesDetails().apply {
            arguments = Bundle().apply {
                putParcelable(MOVIE, movie)
            }
        }
    }
}