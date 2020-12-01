package ru.androidacademy.gooseeye.adapters

import android.content.ContentValues
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

class MovieRecyclerAdapter(private val values: List<MovieInfo>, private val listener: ClickListener) :
    RecyclerView.Adapter<MovieRecyclerAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.moviePoster?.setImageResource(values[position].moviePoster)
        holder.age?.text = values[position].age
        holder.tag?.text = values[position].tag
        holder.reviews?.text = values[position].reviews
        holder.movieName?.text = values[position].movieName
        holder.duration?.text = values[position].duration
        holder.ratingBar?.rating = values[position].rating
        holder.like?.isChecked = values[position].like

        holder.itemView.setOnClickListener {listener.onClick(values[position])}
    }

    override fun getItemCount(): Int = values.size


    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var moviePoster: ShapeableImageView? = null
        var age: TextView? = null
        var tag: TextView? = null
        var reviews: TextView? = null
        var movieName: TextView? = null
        var duration: TextView? = null
        var ratingBar: RatingBarSvg? = null
        var like: CheckBox? = null

        init {
            moviePoster = itemView.findViewById(R.id.iv_movie_poster)
            age = itemView.findViewById(R.id.tv_item_pg)
            tag = itemView.findViewById(R.id.tv_item_tag)
            reviews = itemView.findViewById(R.id.tv_review_item)
            movieName = itemView.findViewById(R.id.tv_movie_name)
            duration = itemView.findViewById(R.id.tv_min)
            ratingBar = itemView.findViewById(R.id.rb_item)
            like = itemView.findViewById(R.id.cb_like)
        }
    }
}

interface ClickListener {
    fun onClick(movieInfo: MovieInfo)
}