package ru.androidacademy.gooseeye.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.androidacademy.gooseeye.R
import ru.androidacademy.gooseeye.data.Actor
import ru.androidacademy.gooseeye.data.Movie
import ru.androidacademy.gooseeye.databinding.ItemArtistBinding

class ArtistRecyclerAdapter() :
    RecyclerView.Adapter<ArtistRecyclerAdapter.ArtistViewHolder>() {

    private var actors = emptyList<Actor>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistViewHolder {
        val binding = ItemArtistBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ArtistViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArtistViewHolder, position: Int) {
        holder.bind(actors[position])
    }

    override fun getItemCount(): Int {
        return actors.size
    }

    class ArtistViewHolder(private val binding: ItemArtistBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(actor: Actor) {
            binding.apply {
                Glide
                    .with(itemView)
                    .load(actor.picture)
                    .placeholder(R.drawable.placeholder_actor)
                    .into(imgArtist)
                tvArtistInfo.text = actor.name
            }
        }
    }

    fun setActors(list: List<Actor>) {
        actors = list
        notifyDataSetChanged()
    }
}