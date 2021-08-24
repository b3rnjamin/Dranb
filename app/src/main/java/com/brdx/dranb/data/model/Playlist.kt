package com.brdx.dranb.data.model

import androidx.annotation.DrawableRes

data class Playlist(
    /**FEATURES**/
    override val id: String = "",
    override val name: String = "undefined",
    val songList: List<Song> = emptyList(),
    @DrawableRes
    val cover: Int = -1,
): Media