package com.sevenzeroes.trekkieapp.core.ui.helpers

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.sevenzeroes.trekkieapp.core.ui.fragment.EpisodesFragment
import com.sevenzeroes.trekkieapp.core.ui.fragment.FavouritesFragment

class MainViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> EpisodesFragment()
            else -> FavouritesFragment()
        }
    }
}
