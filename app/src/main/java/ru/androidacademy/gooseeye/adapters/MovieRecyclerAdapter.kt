package ru.androidacademy.gooseeye.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import ru.androidacademy.gooseeye.R
import ru.androidacademy.gooseeye.RatingBarSvg
import ru.androidacademy.gooseeye.models.MovieInfo

class MovieRecyclerAdapter(private val listener: (MovieInfo) -> Unit) :
    RecyclerView.Adapter<MovieRecyclerAdapter.MovieViewHolder>() {

    private var values = listOf<MovieInfo>()

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

        fun bind(movie: MovieInfo) {
            this.moviePoster.setImageResource(movie.moviePoster)
            this.age.text = movie.age
            this.tag.text = movie.tag
            this.reviews.text = movie.reviews
            this.movieName.text = movie.movieName
            this.duration.text = movie.duration
            this.ratingBar.rating = movie.rating
            this.like.isChecked = movie.like
        }
    }

    fun setMovies(list: List<MovieInfo>) {
        values = list
        notifyDataSetChanged()
    }
}