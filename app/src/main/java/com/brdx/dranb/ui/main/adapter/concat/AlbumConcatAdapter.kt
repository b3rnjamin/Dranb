package com.brdx.dranb.ui.main.adapter.concat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.brdx.dranb.core.BaseConcatHolder
import com.brdx.dranb.databinding.RankingAlbumAdapterBinding
import com.brdx.dranb.ui.main.adapter.RankingAlbumAdapter
import com.brdx.dranb.ui.main.adapter.RankingSongAdapter

class AlbumConcatAdapter(private val albumAdapter: RankingAlbumAdapter) :
    RecyclerView.Adapter<BaseConcatHolder<*>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseConcatHolder<*> {
        val itemBinding =
            RankingAlbumAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ConcatViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: BaseConcatHolder<*>, position: Int) {
        when (holder) {
            is ConcatViewHolder -> holder.bind(albumAdapter)
        }
    }

    override fun getItemCount(): Int = 1

    private inner class ConcatViewHolder(val binding: RankingAlbumAdapterBinding) :
        BaseConcatHolder<RankingAlbumAdapter>(binding.root) {
        override fun bind(adapter: RankingAlbumAdapter) {
            binding.recyclerRanking.adapter = adapter
        }

    }
}