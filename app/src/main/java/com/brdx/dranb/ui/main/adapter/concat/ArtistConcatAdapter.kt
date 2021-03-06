package com.brdx.dranb.ui.main.adapter.concat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.brdx.dranb.core.BaseConcatHolder
import com.brdx.dranb.databinding.RankingArtistAdapterBinding
import com.brdx.dranb.ui.main.adapter.RankingArtistAdapter
import com.brdx.dranb.ui.main.adapter.RankingSongAdapter

class ArtistConcatAdapter(private val artistAdapter: RankingArtistAdapter) :
    RecyclerView.Adapter<BaseConcatHolder<*>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseConcatHolder<*> {
        val itemBinding =
            RankingArtistAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ConcatViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: BaseConcatHolder<*>, position: Int) {
        when (holder) {
            is ConcatViewHolder -> holder.bind(artistAdapter)
        }
    }

    override fun getItemCount(): Int = 1

    private inner class ConcatViewHolder(val binding: RankingArtistAdapterBinding) :
        BaseConcatHolder<RankingArtistAdapter>(binding.root) {
        override fun bind(adapter: RankingArtistAdapter) {
            binding.recyclerRanking.adapter = adapter
        }

    }
}