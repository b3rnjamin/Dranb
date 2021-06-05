package com.brdx.dranb.ui.main.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.brdx.dranb.R
import com.brdx.dranb.databinding.FragmentSongBinding


class SongFragment : Fragment(R.layout.fragment_song) {
    private lateinit var binding: FragmentSongBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSongBinding.bind(view)
    }
}