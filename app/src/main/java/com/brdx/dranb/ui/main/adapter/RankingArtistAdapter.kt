package com.brdx.dranb.ui.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.brdx.dranb.core.BaseViewHolder
import com.brdx.dranb.data.model.Artist
import com.brdx.dranb.databinding.ItemRankingArtistBinding
import com.brdx.dranb.ui.main.interfaces.OnRankingListener
import com.brdx.dranb.util.Extension.basicDiffUtil

class RankingArtistAdapter(
    private val rankingListener: OnRankingListener
) : RecyclerView.Adapter<BaseViewHolder<*>>() {

    var artistList: List<Artist> by basicDiffUtil(
        areItemsTheSame = { old, new -> old.id == new.id },
        areContentsTheSame = { old, new -> old == new }
    )


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<*> {
        val binding =
            ItemRankingArtistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = ArtistViewHolder(binding, parent.context)
        binding.imageSongCover.setOnClickListener {
            val position = holder.bindingAdapterPosition.takeIf {
                it != DiffUtil.DiffResult.NO_POSITION
            } ?: return@setOnClickListener

            rankingListener.onArtistClickListener(artistList[position])
        }
        return holder
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is ArtistViewHolder -> holder.bind(artistList[position], position)
            else -> throw IllegalArgumentException("Not View Available")
        }
    }

    override fun getItemCount(): Int = if (artistList.size < 6) artistList.size else 6

    private inner class ArtistViewHolder(
        private val binding: ItemRankingArtistBinding,
        private val context: Context
    ) :
        BaseViewHolder<Artist>(binding.root) {
        override fun bind(item: Artist, position: Int) {
            binding.textSong.text = "${position + 1} - ${item.name}"
            binding.imageSongCover.setImageDrawable(
                AppCompatResources.getDrawable(
                    context,
                    item.cover
                )
            )
        }
    }
}