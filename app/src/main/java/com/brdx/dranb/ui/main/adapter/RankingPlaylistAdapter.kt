package com.brdx.dranb.ui.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.brdx.dranb.core.BaseViewHolder
import com.brdx.dranb.data.model.Playlist
import com.brdx.dranb.databinding.ItemRankingPlaylistBinding
import com.brdx.dranb.ui.main.interfaces.OnRankingListener
import com.brdx.dranb.util.Extension.basicDiffUtil

class RankingPlaylistAdapter(
    private val rankingListener: OnRankingListener
) : RecyclerView.Adapter<BaseViewHolder<*>>() {

    var playlistList: List<Playlist> by basicDiffUtil(
        areItemsTheSame = { old, new -> old.id == new.id },
        areContentsTheSame = { old, new -> old == new }
    )

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<*> {
        val binding =
            ItemRankingPlaylistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = PlaylistViewHolder(binding, parent.context)
        binding.imageSongCover.setOnClickListener {
            val position = holder.bindingAdapterPosition.takeIf {
                it != DiffUtil.DiffResult.NO_POSITION
            } ?: return@setOnClickListener

            rankingListener.onPlayListClickListener(playlistList[position])
        }
        return holder
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is PlaylistViewHolder -> holder.bind(playlistList[position], position)
            else -> throw IllegalArgumentException("Not View Available")
        }
    }

    override fun getItemCount(): Int = if (playlistList.size < 6) playlistList.size else 6

    private inner class PlaylistViewHolder(
        private val binding: ItemRankingPlaylistBinding,
        private val context: Context
    ) :
        BaseViewHolder<Playlist>(binding.root) {
        override fun bind(item: Playlist, position: Int) {
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