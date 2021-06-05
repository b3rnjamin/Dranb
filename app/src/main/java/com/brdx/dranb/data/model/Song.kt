package com.brdx.dranb.data.model

import androidx.annotation.DrawableRes

data class Song(
    /**FEATURES**/
    val title: String = "undefined",
    val artist: String = "",
    @DrawableRes
    val cover: Int = -1,
    val id: String = ""
    )/**
val duration: Int = 0,
val albumName: String = "undefined",
val albumArtist: String = "undefined",
/**FEATURES**/
val route: String = "undefined",
val count: Int = 0,
val is_favorite: Boolean = false
)**/