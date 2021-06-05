package com.brdx.dranb.ui.main.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.brdx.dranb.R
import com.brdx.dranb.data.model.Song
import com.brdx.dranb.databinding.FragmentMainBinding
import com.brdx.dranb.databinding.FragmentRankingBinding
import com.brdx.dranb.ui.main.adapter.SongAdapter

class RankingFragment : Fragment(R.layout.fragment_ranking), SongAdapter.OnSongClickListener {
    private lateinit var binding: FragmentRankingBinding
    //Context
    private val ctx by lazy { requireContext() }

    private val topSongAdapter by lazy {SongAdapter(ctx, this@RankingFragment)}
    private val topAlbumAdapter by lazy { SongAdapter(ctx,this@RankingFragment) }
    private val topArtistAdapter by lazy { SongAdapter(ctx, this@RankingFragment) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRankingBinding.bind(view)

        binding.gridTopSongs.apply {
            adapter = topSongAdapter
            layoutManager = GridLayoutManager(ctx, 2, RecyclerView.VERTICAL,false)
        }
        binding.gridTopAlbums.apply {
            adapter = topAlbumAdapter
            layoutManager = GridLayoutManager(ctx, 2, RecyclerView.VERTICAL,false)
        }
        binding.gridTopArtists.apply {
            adapter = topArtistAdapter
            layoutManager = GridLayoutManager(ctx, 2, RecyclerView.VERTICAL,false)
        }

        val songList = listOf(
            Song("Side To Side", "Dangerous Woman", R.drawable.cover_1, "0"),
            Song("Levitating", "Future Nostalgia", R.drawable.cover_2, "1"),
            Song("Can´t Get You out of My Head", "Kylie Minogue", R.drawable.cover_6, "5"),
            Song("It's Not Your Fault", "PARANOIA", R.drawable.cover_3, "2"),
            Song("Meaningless", "Charlotte Cardin", R.drawable.cover_4, "3"),
            Song("medicine", "amo", R.drawable.cover_5, "4"),
        )

        topSongAdapter.setData(songList)
        topAlbumAdapter.setData(songList)
        topArtistAdapter.setData(songList)
    }

    override fun onClick(song: Song) {
        TODO("Not yet implemented")
    }
}