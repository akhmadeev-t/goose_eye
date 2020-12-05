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

class MovieRecyclerAdapter(
    private val values: List<MovieInfo>,
    private val listener: ClickListener
) :
    RecyclerView.Adapter<MovieRecyclerAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = values[position]
        holder.bind(movie)
        holder.itemView.setOnClickListener { listener.onClick(movie) }
    }

    override fun getItemCount(): Int = values.size


    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var moviePoster: ShapeableImageView? = itemView.findViewById(R.id.iv_movie_poster)
        var age: TextView? = itemView.findViewById(R.id.tv_item_pg)
        var tag: TextView? = itemView.findViewById(R.id.tv_item_tag)
        var reviews: TextView? = itemView.findViewById(R.id.tv_review_item)
        var movieName: TextView? = itemView.findViewById(R.id.tv_movie_name)
        var duration: TextView? = itemView.findViewById(R.id.tv_min)
        var ratingBar: RatingBarSvg? = itemView.findViewById(R.id.rb_item)
        var like: CheckBox? = itemView.findViewById(R.id.cb_like)

        fun bind(movie: MovieInfo) {
            this.moviePoster?.setImageResource(movie.moviePoster)
            this.age?.text = movie.age
            this.tag?.text = movie.tag
            this.reviews?.text = movie.reviews
            this.movieName?.text = movie.movieName
            this.duration?.text = movie.duration
            this.ratingBar?.rating = movie.rating
            this.like?.isChecked = movie.like
        }
    }
}

interface ClickListener {
    fun onClick(movieInfo: MovieInfo)
}