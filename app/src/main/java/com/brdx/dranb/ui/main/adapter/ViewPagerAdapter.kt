package com.brdx.dranb.ui.main.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(
    private val items: List<Fragment>,
    fragment: Fragment
) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = items.size
    override fun createFragment(position: Int): Fragment = items[position]
}