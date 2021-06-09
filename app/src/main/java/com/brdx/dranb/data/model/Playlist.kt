package com.brdx.dranb.data.model

import androidx.annotation.DrawableRes

data class Playlist(
    /**FEATURES**/
    val title: String = "undefined",
    val songList: List<Song> = emptyList(),
    @DrawableRes
    val cover: Int = -1,
    val id: String = ""
)