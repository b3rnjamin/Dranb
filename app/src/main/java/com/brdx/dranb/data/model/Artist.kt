package com.brdx.dranb.data.model

import androidx.annotation.DrawableRes

data class Artist(
    override val id: String = "",
    override val name: String = "",
    @DrawableRes
    val cover: Int = -1,
) : Media