package com.brdx.dranb.util

import android.view.View
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.conflate
import kotlin.properties.Delegates

object Extension {
    fun <VH : RecyclerView.ViewHolder, T> RecyclerView.Adapter<VH>.basicDiffUtil(
        initialValue: List<T> = emptyList(),
        areItemsTheSame: (T, T) -> Boolean = { old, new -> old == new },
        areContentsTheSame: (T, T) -> Boolean = { old, new -> old == new }
    ) =
        Delegates.observable(initialValue) { _, oldValue, newValue ->
            DiffUtil.calculateDiff(object : DiffUtil.Callback() {
                override fun getOldListSize(): Int = oldValue.size
                override fun getNewListSize(): Int = newValue.size
                override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                    areItemsTheSame(oldValue[oldItemPosition], newValue[newItemPosition])

                override fun areContentsTheSame(
                    oldItemPosition: Int,
                    newItemPosition: Int
                ): Boolean =
                    areContentsTheSame(oldValue[oldItemPosition], newValue[newItemPosition])
            }).also {
                it.dispatchUpdatesTo(this)
            }
        }

    @ExperimentalCoroutinesApi
    val View.onClickEvents: Flow<View>
        get() = callbackFlow {
            val onClickListener = View.OnClickListener { trySend(it).isSuccess }
            setOnClickListener(onClickListener)
            awaitClose { setOnClickListener(null) }
        }.conflate()

    @ExperimentalCoroutinesApi
    val RecyclerView.hideEvents: Flow<Int>
        get() = callbackFlow<Int> {
            val listener = object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    trySend(dy)
                }
            }
            addOnScrollListener(listener)
            awaitClose { removeOnScrollListener(listener) }
        }.conflate()


    var View.visible: Boolean
        get() = visibility == View.VISIBLE
        set(value) {
            visibility = if (value) View.VISIBLE else View.GONE
        }
}