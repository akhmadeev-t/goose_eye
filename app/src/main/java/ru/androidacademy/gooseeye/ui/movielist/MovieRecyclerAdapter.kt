package ru.androidacademy.gooseeye.ui.movielist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.androidacademy.gooseeye.R
import ru.androidacademy.gooseeye.data.models.Movie
import ru.androidacademy.gooseeye.databinding.ItemMovieBinding

class MovieRecyclerAdapter(private val listener: (Movie) -> Unit) :
    RecyclerView.Adapter<MovieRecyclerAdapter.MovieViewHolder>() {

    private var movies = emptyList<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
        holder.itemView.setOnClickListener { listener(movie) }
    }

    override fun getItemCount(): Int = movies.size

    class MovieViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.apply {
                Glide
                    .with(itemView)
                    .load(movie.poster)
                    .placeholder(R.drawable.placeholder_movie)
                    .into(ivMoviePoster)
                tvItemPg.text =
                    "${movie.minimumAge}${itemView.resources.getString(R.string.pg_text)}"
                tvItemTag.text = movie.genres.joinToString { it.name }
                tvReviewItem.text =
                    "${movie.numberOfRatings} ${
                        itemView.resources.getQuantityString(
                            R.plurals.review_plurals,
                            movie.numberOfRatings
                        )
                    }"
                tvMovieName.text = movie.title
                tvRelease.text = movie.release
                rbItem.rating = (movie.ratings) / 2
                cbLike.isChecked = false
            }
        }
    }

    fun setMovies(list: List<Movie>) {
        movies = list
        notifyDataSetChanged()
    }
}