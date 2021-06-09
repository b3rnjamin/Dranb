package com.brdx.dranb.ui.main.fragment

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.*
import androidx.recyclerview.widget.ConcatAdapter
import com.brdx.dranb.R
import com.brdx.dranb.data.model.Song
import com.brdx.dranb.databinding.FragmentRankingBinding
import com.brdx.dranb.presentation.main.RankingViewModel
import com.brdx.dranb.presentation.main.RankingViewState
import com.brdx.dranb.ui.main.adapter.RankingSongAdapter
import com.brdx.dranb.ui.main.adapter.concat.AlbumConcatAdapter
import com.brdx.dranb.ui.main.adapter.concat.ArtistConcatAdapter
import com.brdx.dranb.ui.main.adapter.concat.SongConcatAdapter
import com.brdx.dranb.util.Extension.visible
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.*

class RankingFragment :
    Fragment(R.layout.fragment_ranking),
    RankingSongAdapter.OnSongClickListener {
    private lateinit var binding: FragmentRankingBinding

    //Context
    private val ctx by lazy { requireContext() }

    private val topSongAdapter by lazy { RankingSongAdapter(this@RankingFragment) }
    private val topAlbumAdapter by lazy { RankingSongAdapter(this@RankingFragment) }
    private val topArtistAdapter by lazy { RankingSongAdapter(this@RankingFragment) }

    private val viewModel by activityViewModels<RankingViewModel>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRankingBinding.bind(view)

        val songList = listOf(
            Song("Side To Side", "Dangerous Woman", R.drawable.cover_1, "0"),
            Song("Levitating", "Future Nostalgia", R.drawable.cover_2, "1"),
            Song("Can´t Get You out of My Head", "Kylie Minogue", R.drawable.cover_6, "5"),
            Song("It's Not Your Fault", "PARANOIA", R.drawable.cover_3, "2"),
            Song("Meaningless", "Charlotte Cardin", R.drawable.cover_4, "3"),
            Song("medicine", "amo", R.drawable.cover_5, "4")
        )

        topSongAdapter.songList = songList
        topAlbumAdapter.songList = songList
        topArtistAdapter.songList = songList

        val concatAdapter = ConcatAdapter()

        concatAdapter.apply {
            addAdapter(0, SongConcatAdapter(topSongAdapter))
            addAdapter(1, AlbumConcatAdapter(topAlbumAdapter))
            addAdapter(2, ArtistConcatAdapter(topArtistAdapter))
        }

        binding.recyclerRanking.adapter = concatAdapter

    /*
        viewModel.songList.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .onEach { songList ->
                concatAdapter.addAdapter(0, SongConcatAdapter(topSongAdapter))
                concatAdapter.addAdapter(1, AlbumConcatAdapter(topAlbumAdapter))
                concatAdapter.addAdapter(2, ArtistConcatAdapter(topArtistAdapter))

                topSongAdapter.songList = songList
                topAlbumAdapter.songList = songList
                topArtistAdapter.songList = songList

                binding.recyclerRanking.adapter = concatAdapter

            }.launchIn(lifecycleScope)
        viewModel.viewState.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED).onEach(::render)
            .launchIn(lifecycleScope)*/

        /*addRepeatingJob(Lifecycle.State.STARTED) {
            launch {
                viewModel.songList.collect { songList ->
                    concatAdapter.apply {
                        addAdapter(0, topSongAdapter)
                        addAdapter(1, topAlbumAdapter)
                        addAdapter(2, topArtistAdapter)
                    }

                    topSongAdapter.songList = songList
                    topAlbumAdapter.songList = songList
                    topArtistAdapter.songList = songList

                    binding.recyclerRanking.adapter = concatAdapter
                }
            }
            launch {
                viewModel.viewState.collect(::render)
            }
            // viewModel.process(RankingEvent.LoadRanking)


            /*binding.gridTopSongs.hideEvents.collect {
                Snackbar.make(binding.rootLayout, it.toString(), Snackbar.LENGTH_SHORT)
                    .setAnchorView(activity?.findViewById(R.id.bottomNavigationView))
                    .show() }*/
        }*/

        //viewModel.process(RankingEvent.LoadRanking)
        /*lifecycleScope.launchWhenStarted {
        }*/
    }


    private fun render(viewState: RankingViewState) = with(binding) {
        progressBar.visible = viewState.loading
        message.isVisible = viewState.error != null
        viewState.error?.let(message::setText)
    }

    override fun onClick(song: Song) {
        Snackbar.make(binding.root, song.title, Snackbar.LENGTH_SHORT)
            .setAnchorView(activity?.findViewById(R.id.bottomNavigationView))
            .show()
    }
}