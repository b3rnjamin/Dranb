package com.brdx.dranb.ui.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.brdx.dranb.core.BaseViewHolder
import com.brdx.dranb.data.model.Song
import com.brdx.dranb.databinding.ItemRankingBinding

class SongAdapter(
    private val context: Context,
    private val itemClickListener: OnSongClickListener
) : RecyclerView.Adapter<BaseViewHolder<*>>() {

    private var songList: List<Song> = emptyList()

    fun setData(newSongList: List<Song>) {
        val diffUtil = RankingSongDiffUtil(songList, newSongList)
        val diffResults = DiffUtil.calculateDiff(diffUtil)
        diffResults.dispatchUpdatesTo(this@SongAdapter)
        songList = newSongList
    }

    interface OnSongClickListener {
        fun onClick(song: Song)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<*> {
        val binding = ItemRankingBinding.inflate(LayoutInflater.from(context), parent, false)
        val holder = SongViewHolder(binding)

        binding.imageSongCover.setOnClickListener {
            val position = holder.adapterPosition.takeIf {
                it != DiffUtil.DiffResult.NO_POSITION
            } ?: return@setOnClickListener

            itemClickListener.onClick(songList[position])
        }

        return holder
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is SongViewHolder -> holder.bind(songList[position])
            else -> throw IllegalArgumentException("Not View Available")
        }
    }

    override fun getItemCount(): Int = 3

    inner class SongViewHolder(private val itemLayout: ItemRankingBinding) :
        BaseViewHolder<Song>(itemLayout.root) {
        override fun bind(item: Song) {
            itemLayout.textSong.text = item.title
            itemLayout.imageSongCover.setImageDrawable(
                AppCompatResources.getDrawable(
                    context,
                    item.cover
                )
            )
        }
    }

    inner class RankingSongDiffUtil(
        private val oldList: List<Song>,
        private val newList: List<Song>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int = oldList.size
        override fun getNewListSize(): Int = newList.size
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldList[oldItemPosition].id == newList[newItemPosition].id

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldList[oldItemPosition] == newList[newItemPosition]
    }
}