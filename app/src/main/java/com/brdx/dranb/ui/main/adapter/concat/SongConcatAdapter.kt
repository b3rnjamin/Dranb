package com.brdx.dranb.ui.main.adapter.concat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.brdx.dranb.core.BaseConcatHolder
import com.brdx.dranb.databinding.RankingSongAdapterBinding
import com.brdx.dranb.ui.main.adapter.RankingSongAdapter

class SongConcatAdapter(private val rankingSongAdapter: RankingSongAdapter) :
    RecyclerView.Adapter<BaseConcatHolder<*>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseConcatHolder<*> {
        val itemBinding =
            RankingSongAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ConcatViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: BaseConcatHolder<*>, position: Int) {
        when (holder) {
            is ConcatViewHolder -> holder.bind(rankingSongAdapter)
        }
    }

    override fun getItemCount(): Int = 1

    private inner class ConcatViewHolder(val binding: RankingSongAdapterBinding) :
        BaseConcatHolder<RankingSongAdapter>(binding.root) {
        override fun bind(adapter: RankingSongAdapter) {
            binding.recyclerRanking.adapter = adapter
        }

    }
}