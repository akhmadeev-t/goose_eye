package ru.androidacademy.gooseeye.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.androidacademy.gooseeye.models.ArtistInfo
import ru.androidacademy.gooseeye.adapters.ArtistRecyclerAdapter
import ru.androidacademy.gooseeye.R

class FragmentMoviesDetails : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_movies_details, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerViewArtist()
        val backButton = view.findViewById<TextView>(R.id.btn_back)
        backButton.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()?.apply {
                remove(this@FragmentMoviesDetails)
                setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                commit()
            }
        }
    }

    private fun setUpRecyclerViewArtist() {
        val recyclerView = view?.findViewById<RecyclerView>(R.id.rv_artist_list)
        val layoutManager = LinearLayoutManager(requireActivity())
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        val divider = DividerItemDecoration(recyclerView?.context, layoutManager.orientation)
        divider.setDrawable(
            ContextCompat.getDrawable(
                recyclerView?.context!!,
                R.drawable.divider_separator
            )!!
        )
        recyclerView.apply {
            addItemDecoration(divider)
            this.layoutManager = layoutManager
            this.adapter = ArtistRecyclerAdapter(fillList())
        }
    }

    private fun fillList(): List<ArtistInfo> {
        return listOf(
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