package ru.androidacademy.gooseeye

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView

class ArtistRecyclerAdapter(private val values: List<ArtistInfo>) :
    RecyclerView.Adapter<ArtistRecyclerAdapter.ArtistViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_artist, parent, false)
        return ArtistViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ArtistViewHolder, position: Int) {
        holder.artistPhoto?.setImageResource(values[position].artistPhoto)
        holder.artistName?.text = values[position].artistName
    }

    override fun getItemCount(): Int {
        return values.size
    }

    class ArtistViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var artistPhoto: ShapeableImageView? = null
        var artistName: TextView? = null

        init {
            artistPhoto = itemView.findViewById(R.id.img_artist)
            artistName = itemView.findViewById(R.id.tv_artist_info)
        }
    }
}