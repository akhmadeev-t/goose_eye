package ru.androidacademy.gooseeye.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import ru.androidacademy.gooseeye.R
import ru.androidacademy.gooseeye.RatingBarSvg
import ru.androidacademy.gooseeye.data.Movie

class MovieRecyclerAdapter(private val listener: (Movie) -> Unit) :
    RecyclerView.Adapter<MovieRecyclerAdapter.MovieViewHolder>() {

    private var values = listOf<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = values[position]
        holder.bind(movie)
        holder.itemView.setOnClickListener { listener(movie) }
    }

    override fun getItemCount(): Int = values.size


    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val moviePoster: ShapeableImageView = itemView.findViewById(R.id.iv_movie_poster)
        private val age: TextView = itemView.findViewById(R.id.tv_item_pg)
        private val tag: TextView = itemView.findViewById(R.id.tv_item_tag)
        private val reviews: TextView = itemView.findViewById(R.id.tv_review_item)
        private val movieName: TextView = itemView.findViewById(R.id.tv_movie_name)
        private val duration: TextView = itemView.findViewById(R.id.tv_min)
        private val ratingBar: RatingBarSvg = itemView.findViewById(R.id.rb_item)
        private val like: CheckBox = itemView.findViewById(R.id.cb_like)

        fun bind(movie: Movie) {
            Glide.with(itemView).load(movie.poster).into(moviePoster)
            age.text = "${movie.minimumAge}${itemView.resources.getString(R.string.pg_text)}"
            tag.text = movie.getGenres()
            reviews.text =
                "${movie.numberOfRatings} ${itemView.resources.getString(R.string.review_text)}"
            movieName.text = movie.title
            duration.text = "${movie.runtime} ${itemView.resources.getString(R.string.item_min)}"
            ratingBar.rating = (movie.ratings) / 2
            like.isChecked = false
        }
    }

    fun setMovies(list: List<Movie>) {
        values = list
        notifyDataSetChanged()
    }
}