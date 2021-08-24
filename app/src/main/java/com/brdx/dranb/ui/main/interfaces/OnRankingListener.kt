package com.brdx.dranb.ui.main.interfaces

import com.brdx.dranb.data.model.Album
import com.brdx.dranb.data.model.Artist
import com.brdx.dranb.data.model.Playlist
import com.brdx.dranb.data.model.Song
import com.brdx.dranb.data.model.Media

interface OnRankingListener {
    fun onSongCLickListener(song: Song)
    fun onAlbumClickListener(album: Album)
    fun onArtistClickListener(artist: Artist)
    fun onPlayListClickListener(playlist: Playlist)
    fun onMoreClickListener(media: Media)
}