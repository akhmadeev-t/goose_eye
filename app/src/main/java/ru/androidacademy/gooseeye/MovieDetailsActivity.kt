package ru.androidacademy.gooseeye

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MovieDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        setUpRecyclerViewArtist()
    }

    private fun setUpRecyclerViewArtist() {
        val recyclerView = findViewById<RecyclerView>(R.id.rv_artist_list)
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        val divider = DividerItemDecoration(recyclerView.context, layoutManager.orientation)
        divider.setDrawable(ContextCompat.getDrawable(recyclerView.context, R.drawable.divider_separator)!!)
        recyclerView.addItemDecoration(divider)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = ArtistRecyclerAdapter(fillList())
    }

    private fun fillList(): List<ArtistInfo> {
        return mutableListOf(
            ArtistInfo(R.drawable.robert_downey, getString(R.string.robert_downey)),
            ArtistInfo(R.drawable.chris_evans, getString(R.string.chris_evans)),
            ArtistInfo(R.drawable.mark_ruffalo, getString(R.string.mark_ruffalo)),
            ArtistInfo(R.drawable.chris_hemsworth, getString(R.string.chris_hemsworth)),
            ArtistInfo(R.drawable.scarlett_johansson, getString(R.string.scarlett_johansson)),
            ArtistInfo(R.drawable.jeremy_renner, getString(R.string.jeremy_renner)),
            ArtistInfo(R.drawable.don_cheadle, getString(R.string.don_cheadle)),
            ArtistInfo(R.drawable.paul_rudd, getString(R.string.paul_rudd)),
            ArtistInfo(R.drawable.brie_larson, getString(R.string.brie_larson))
        )
    }
}