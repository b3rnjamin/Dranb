package com.brdx.dranb.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brdx.dranb.R
import com.brdx.dranb.data.model.Song
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RankingViewModel : ViewModel() {

    private val _songList = MutableStateFlow<List<Song>>(emptyList())
    private val _viewState = MutableStateFlow(RankingViewState())

    val viewState: StateFlow<RankingViewState> = _viewState

    val songList: StateFlow<List<Song>> = _songList

    init {
        fetchTopSongs()
        //process(RankingEvent.LoadRanking)
        //getSongList()
    }


    fun process(event: RankingEvent) = when(event){
        is RankingEvent.LoadRanking -> fetchTopSongs()
    }

    private fun fetchTopSongs() {
        val __songList = listOf(
            Song("Side To Side", "Dangerous Woman", R.drawable.cover_1, "0"),
            Song("Levitating", "Future Nostalgia", R.drawable.cover_2, "1"),
            Song("Can´t Get You out of My Head", "Kylie Minogue", R.drawable.cover_6, "5"),
            Song("It's Not Your Fault", "PARANOIA", R.drawable.cover_3, "2"),
            Song("Meaningless", "Charlotte Cardin", R.drawable.cover_4, "3"),
            Song("medicine", "amo", R.drawable.cover_5, "4")
        )

        viewModelScope.launch {
            /*_viewState.value = RankingViewState(true)
            delay(750)
            _viewState.value = RankingViewState(error = "omg")
            delay(750)
            _viewState.value = RankingViewState(true)
            delay(750)
            _viewState.value = RankingViewState(false)*/
            _songList.value = __songList
           /* __songList.forEach { song ->
                    delay(750)
                    _songList.value += listOf(song)
                }*/
        }
    }

}