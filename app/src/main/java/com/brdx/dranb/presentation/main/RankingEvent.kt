package com.brdx.dranb.presentation.main

sealed class RankingEvent {
    object LoadRanking : RankingEvent()

    override fun equals(other: Any?): Boolean {
        return false
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}