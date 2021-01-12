package ru.androidacademy.gooseeye.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.androidacademy.gooseeye.R
import ru.androidacademy.gooseeye.RatingBarSvg
import ru.androidacademy.gooseeye.adapters.ArtistRecyclerAdapter
import ru.androidacademy.gooseeye.data.Actor
import ru.androidacademy.gooseeye.data.Movie

class FragmentMoviesDetails : Fragment() {

    private lateinit var movie: Movie

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_movies_details, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movie = arguments?.getParcelable(MOVIE)!!
        Glide
            .with(view)
            .load(movie.backdrop)
            .into(view.findViewById(R.id.img_poster))
        view.findViewById<TextView>(R.id.tv_pg).text =
            "${movie.minimumAge}${view.resources.getString(R.string.pg_text)}"
        view.findViewById<TextView>(R.id.tv_name).text = movie.title
        view.findViewById<TextView>(R.id.tv_tag_line).text = movie.getGenres()
        view.findViewById<RatingBarSvg>(R.id.ratingBar).rating = (movie.ratings) / 2
        view.findViewById<TextView>(R.id.tv_review).text =
            "${movie.numberOfRatings} ${view.resources.getString(R.string.review_text)}"
        view.findViewById<TextView>(R.id.tv_story_line_details).text = movie.overview
        val backButton = view.findViewById<TextView>(R.id.btn_back)
        setUpRecyclerViewArtist()
        backButton.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()?.apply {
                remove(this@FragmentMoviesDetails)
                setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                commit()
            }
        }
    }

    private fun setUpRecyclerViewArtist() {
        val recyclerView = view?.findViewById<RecyclerView>(R.id.rv_artist_list)
        val linearLayoutManager = LinearLayoutManager(requireActivity())
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        val divider = DividerItemDecoration(recyclerView?.context, linearLayoutManager.orientation)
        divider.setDrawable(
            ContextCompat.getDrawable(
                recyclerView?.context!!,
                R.drawable.divider_separator
            )!!
        )
        recyclerView.apply {
            addItemDecoration(divider)
            layoutManager = linearLayoutManager
            adapter = ArtistRecyclerAdapter(fillList())
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