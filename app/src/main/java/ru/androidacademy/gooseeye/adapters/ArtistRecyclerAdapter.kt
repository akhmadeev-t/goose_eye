package ru.androidacademy.gooseeye.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import ru.androidacademy.gooseeye.R
import ru.androidacademy.gooseeye.data.Actor

class ArtistRecyclerAdapter(private val values: List<Actor>) :
    RecyclerView.Adapter<ArtistRecyclerAdapter.ArtistViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_artist, parent, false)
        return ArtistViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ArtistViewHolder, position: Int) {
        holder.bind(values[position])
    }

    override fun getItemCount(): Int {
        return values.size
    }

    class ArtistViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val artistPhoto: ShapeableImageView = itemView.findViewById(R.id.img_artist)
        private val artistName: TextView = itemView.findViewById(R.id.tv_artist_info)

        fun bind(actor: Actor) {
            Glide
                .with(itemView)
                .load(actor.picture)
                .placeholder(R.drawable.placeholder_actor)
                .into(artistPhoto)
            artistName.text = actor.name
        }
    }
}