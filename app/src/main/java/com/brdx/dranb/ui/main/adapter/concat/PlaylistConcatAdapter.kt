package com.brdx.dranb.ui.main.adapter.concat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.brdx.dranb.core.BaseConcatHolder
import com.brdx.dranb.databinding.RankingPlaylistAdapterBinding
import com.brdx.dranb.ui.main.adapter.RankingPlaylistAdapter

class PlaylistConcatAdapter(private val playlistAdapter: RankingPlaylistAdapter) :
    RecyclerView.Adapter<BaseConcatHolder<*>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseConcatHolder<*> {
        val itemBinding =
            RankingPlaylistAdapterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ConcatViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: BaseConcatHolder<*>, position: Int) {
        when (holder) {
            is ConcatViewHolder -> holder.bind(playlistAdapter)
        }
    }

    override fun getItemCount(): Int = 1

    private inner class ConcatViewHolder(val binding: RankingPlaylistAdapterBinding) :
        BaseConcatHolder<RankingPlaylistAdapter>(binding.root) {
        override fun bind(adapter: RankingPlaylistAdapter) {
            binding.recyclerRanking.adapter = adapter
        }

    }
}