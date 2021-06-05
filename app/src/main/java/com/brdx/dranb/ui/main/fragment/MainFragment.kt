package com.brdx.dranb.ui.main.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.brdx.dranb.R
import com.brdx.dranb.databinding.FragmentMainBinding
import com.brdx.dranb.ui.main.adapter.ViewPagerAdapter

class MainFragment : Fragment(R.layout.fragment_main) {
    private lateinit var binding: FragmentMainBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)

        val fragmentList = listOf(
            RankingFragment(),
            SongFragment(),
            SongFragment()
        )
        binding.viewPager.adapter = ViewPagerAdapter(fragmentList, this)
        binding.dotsIndicator.setViewPager2(binding.viewPager)
    }
}