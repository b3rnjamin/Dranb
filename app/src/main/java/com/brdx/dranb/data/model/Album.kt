package com.brdx.dranb.data.model

import androidx.annotation.DrawableRes

data class Album(
    /**FEATURES**/
    val title: String = "undefined",
    val artist: String = "",
    @DrawableRes
    val cover: Int = -1,
    val id: String = ""
)