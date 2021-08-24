package com.brdx.dranb.ui.main.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.brdx.dranb.R
import com.brdx.dranb.databinding.FragmentHomeBinding
import com.brdx.dranb.ui.main.adapter.ViewPagerAdapter

class HomeFragment : Fragment(R.layout.fragment_home) {
    private lateinit var binding: FragmentHomeBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        val fragmentList = listOf(
            RankingFragment(),
            SongFragment(),
            SongFragment()
        )

        binding.viewPager.adapter = ViewPagerAdapter(fragmentList, this)
        binding.dotsIndicator.setViewPager2(binding.viewPager)
    }
}