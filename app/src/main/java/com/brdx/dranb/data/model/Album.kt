package com.brdx.dranb.data.model

import androidx.annotation.DrawableRes

data class Album(
    /**FEATURES**/
    override val id: String = "",
    override val name: String = "undefined",
    val artist: String = "",
    @DrawableRes
    val cover: Int = -1,
) : Media