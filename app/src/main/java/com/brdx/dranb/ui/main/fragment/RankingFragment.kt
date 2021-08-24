package com.brdx.dranb.ui.main.fragment

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.*
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView
import com.brdx.dranb.R
import com.brdx.dranb.data.model.*
import com.brdx.dranb.databinding.FragmentRankingBinding
import com.brdx.dranb.presentation.main.RankingViewModel
import com.brdx.dranb.presentation.main.RankingViewState
import com.brdx.dranb.ui.main.adapter.*
import com.brdx.dranb.ui.main.adapter.concat.AlbumConcatAdapter
import com.brdx.dranb.ui.main.adapter.concat.ArtistConcatAdapter
import com.brdx.dranb.ui.main.adapter.concat.PlaylistConcatAdapter
import com.brdx.dranb.ui.main.adapter.concat.SongConcatAdapter
import com.brdx.dranb.ui.main.interfaces.OnRankingListener
import com.brdx.dranb.ui.main.interfaces.OnScrollListener
import com.brdx.dranb.util.Extension.visible
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.*

class RankingFragment :
    Fragment(R.layout.fragment_ranking),
    OnRankingListener {


    private lateinit var binding: FragmentRankingBinding

    //Context
    private val ctx by lazy { requireContext() }

    private val topSongAdapter by lazy { RankingSongAdapter(this@RankingFragment) }
    private val topAlbumAdapter by lazy { RankingAlbumAdapter(this@RankingFragment) }
    private val topArtistAdapter by lazy { RankingArtistAdapter(this@RankingFragment) }
    private val topPlaylistAdapter by lazy { RankingPlaylistAdapter(this@RankingFragment) }

    private val viewModel by activityViewModels<RankingViewModel>()

    private val callbackScroll: OnScrollListener by lazy { activity as OnScrollListener }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRankingBinding.bind(view)

        val songList = listOf(
            Song("0", "Side To Side", "Dangerous Woman", R.drawable.cover_1),
            Song("1", "Levitating", "Future Nostalgia", R.drawable.cover_2),
            Song("2", "Can´t Get You out of My Head", "Kylie Minogue", R.drawable.cover_6),
            Song("3", "It's Not Your Fault", "PARANOIA", R.drawable.cover_3),
            Song("4", "Meaningless", "Charlotte Cardin", R.drawable.cover_4),
            Song("5", "medicine", "amo", R.drawable.cover_5)
        )
        val albumList = listOf(
            Album("0", "Dangerous Woman", "Ariana Grande", R.drawable.cover_1),
            Album("1", "Dangerous Woman", "Ariana Grande", R.drawable.cover_2),
            Album("2", "Dangerous Woman", "Ariana Grande", R.drawable.cover_3),
            Album("3", "Dangerous Woman", "Ariana Grande", R.drawable.cover_4),
            Album("4", "Dangerous Woman", "Ariana Grande", R.drawable.cover_5),
            Album("5", "Dangerous Woman", "Ariana Grande", R.drawable.cover_6),
        )
        val artistList = listOf(
            Artist("0", "Side To Side", R.drawable.cover_1),
            Artist("1", "Side To Side", R.drawable.cover_2),
            Artist("2", "Side To Side", R.drawable.cover_3),
            Artist("3", "Side To Side", R.drawable.cover_4),
            Artist("4", "Side To Side", R.drawable.cover_5),
            Artist("5", "Side To Side", R.drawable.cover_6),
        )
        val playlistList = listOf(
            Playlist("0", "Side To Side", emptyList(), R.drawable.cover_1),
            Playlist("1", "Side To Side", emptyList(), R.drawable.cover_2),
            Playlist("2", "Side To Side", emptyList(), R.drawable.cover_3),
            Playlist("3", "Side To Side", emptyList(), R.drawable.cover_4),
            Playlist("4", "Side To Side", emptyList(), R.drawable.cover_5),
            Playlist("5", "Side To Side", emptyList(), R.drawable.cover_6),
        )

        topSongAdapter.songList = songList
        topAlbumAdapter.albumList = albumList
        topArtistAdapter.artistList = artistList
        topPlaylistAdapter.playlistList = playlistList

        val concatAdapter = ConcatAdapter()

        concatAdapter.apply {
            addAdapter(0, SongConcatAdapter(topSongAdapter))
            addAdapter(1, AlbumConcatAdapter(topAlbumAdapter))
            addAdapter(2, ArtistConcatAdapter(topArtistAdapter))
            addAdapter(3, PlaylistConcatAdapter(topPlaylistAdapter))
        }

        binding.recyclerRanking.adapter = concatAdapter
        binding.recyclerRanking.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                callbackScroll.onScrolled(dy > 24)
            }
        })

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

    override fun onSongCLickListener(song: Song) {
        Snackbar.make(binding.root, song.name, Snackbar.LENGTH_SHORT)
            .setAnchorView(activity?.findViewById(R.id.bottomNavigationView))
            .show()
    }

    override fun onAlbumClickListener(album: Album) {
        Snackbar.make(binding.root, album.name, Snackbar.LENGTH_SHORT)
            .setAnchorView(activity?.findViewById(R.id.bottomNavigationView))
            .show()
    }

    override fun onArtistClickListener(artist: Artist) {
        Snackbar.make(binding.root, artist.name, Snackbar.LENGTH_SHORT)
            .setAnchorView(activity?.findViewById(R.id.bottomNavigationView))
            .show()
    }

    override fun onPlayListClickListener(playlist: Playlist) {
        Snackbar.make(binding.root, playlist.name, Snackbar.LENGTH_SHORT)
            .setAnchorView(activity?.findViewById(R.id.bottomNavigationView))
            .show()
    }

    override fun onMoreClickListener(media: Media) {
        Snackbar.make(binding.root, media.name, Snackbar.LENGTH_SHORT)
            .setAnchorView(activity?.findViewById(R.id.bottomNavigationView))
            .show()
    }
}