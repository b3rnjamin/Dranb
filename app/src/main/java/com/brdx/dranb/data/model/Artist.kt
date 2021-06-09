package com.brdx.dranb.data.model

import androidx.annotation.DrawableRes

data class Artist(
    val name: String = "",
    @DrawableRes
    val cover: Int = -1,
    val id: String = ""
    )