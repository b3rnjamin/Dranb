package com.brdx.dranb.ui.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.brdx.dranb.core.BaseViewHolder
import com.brdx.dranb.data.model.Album
import com.brdx.dranb.databinding.ItemRankingAlbumBinding
import com.brdx.dranb.ui.main.interfaces.OnRankingListener
import com.brdx.dranb.util.Extension.basicDiffUtil

class RankingAlbumAdapter(
    private val rankingListener: OnRankingListener
) : RecyclerView.Adapter<BaseViewHolder<Album>>() {

    var albumList: List<Album> by basicDiffUtil(
        initialValue = emptyList(),
        areItemsTheSame = { old, new -> old.id == new.id },
        areContentsTheSame = { old, new -> old == new }
    )

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<Album> {
        val binding =
            ItemRankingAlbumBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = AlbumViewHolder(binding, parent.context)
        binding.imageSongCover.setOnClickListener {
            val position = holder.bindingAdapterPosition.takeIf {
                it != DiffUtil.DiffResult.NO_POSITION
            } ?: return@setOnClickListener

            rankingListener.onAlbumClickListener(albumList[position])
        }
        return holder
    }

    override fun onBindViewHolder(holder: BaseViewHolder<Album>, position: Int) {
        when (holder) {
            is AlbumViewHolder -> holder.bind(albumList[position], position)
            else -> throw IllegalArgumentException("Not View Available")
        }
    }

/*    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is AlbumViewHolder -> holder.bind(albumList[position], position)
            else -> throw IllegalArgumentException("Not View Available")
        }
    }*/

    override fun getItemCount(): Int = if (albumList.size < 6) albumList.size else 6

    private inner class AlbumViewHolder(
        private val binding: ItemRankingAlbumBinding,
        private val context: Context
    ) :
        BaseViewHolder<Album>(binding.root) {
        override fun bind(item: Album, position: Int) {
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