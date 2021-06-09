package com.brdx.dranb.ui.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.brdx.dranb.core.BaseViewHolder
import com.brdx.dranb.data.model.Song
import com.brdx.dranb.databinding.ItemRankingSongBinding
import com.brdx.dranb.util.Extension.basicDiffUtil

class RankingSongAdapter(
    private val itemClickListener: OnSongClickListener
) : RecyclerView.Adapter<BaseViewHolder<*>>() {

    var songList: List<Song> by basicDiffUtil(
        areItemsTheSame = { old, new -> old.id == new.id },
        areContentsTheSame = { old, new -> old == new }
    )

    interface OnSongClickListener {
        fun onClick(song: Song)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<*> {
        val binding = ItemRankingSongBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = SongViewHolder(binding, parent.context)
        binding.imageSongCover.setOnClickListener {
            val position = holder.bindingAdapterPosition.takeIf {
                it != DiffUtil.DiffResult.NO_POSITION
            } ?: return@setOnClickListener

            itemClickListener.onClick(songList[position])
        }
        return holder
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is SongViewHolder -> holder.bind(songList[position], position)
            else -> throw IllegalArgumentException("Not View Available")
        }
    }

    override fun getItemCount(): Int = if (songList.size < 6) songList.size else 6

    private inner class SongViewHolder(
        private val binding: ItemRankingSongBinding,
        private val context: Context
    ) :
        BaseViewHolder<Song>(binding.root) {
        override fun bind(item: Song, position: Int) {
            binding.textSong.text = "${position + 1} - ${item.title}"
            binding.imageSongCover.setImageDrawable(
                AppCompatResources.getDrawable(
                    context,
                    item.cover
                )
            )
        }
    }
}