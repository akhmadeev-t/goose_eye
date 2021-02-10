package ru.androidacademy.gooseeye.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.androidacademy.gooseeye.R
import ru.androidacademy.gooseeye.data.Actor
import ru.androidacademy.gooseeye.databinding.ItemArtistBinding

class ArtistRecyclerAdapter(private val values: List<Actor>) :
    RecyclerView.Adapter<ArtistRecyclerAdapter.ArtistViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistViewHolder {
        val binding = ItemArtistBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ArtistViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArtistViewHolder, position: Int) {
        holder.bind(values[position])
    }

    override fun getItemCount(): Int {
        return values.size
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
}