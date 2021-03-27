package com.sevenzeroes.trekkieapp.list.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.sevenzeroes.trekkieapp.R
import com.sevenzeroes.trekkieapp.databinding.MainViewpagerBinding


class MainActivity : AppCompatActivity()  {

    private lateinit var binding: MainViewpagerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainViewpagerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewPager()
        initBnvListener()
    }

    private fun setupViewPager(){
        binding.vpMain.adapter = ScreenSlidePagerAdapter(this)
        binding.vpMain.isUserInputEnabled = false
    }

    private fun initBnvListener() {
        binding.bnvMain.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.bnvListItem -> openList()
                R.id.bnvFavouriteItem -> openFavourites()
            }
            true
        }

    }

    private fun openList(){
        binding.vpMain.currentItem = 0
    }

    private fun openFavourites(){
        binding.vpMain.currentItem = 1
    }

    private inner class ScreenSlidePagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
        override fun getItemCount(): Int = 2

        override fun createFragment(position: Int): Fragment {
           return when(position){
                0 -> EpisodesFragment()
               else -> FavouritesFragment()
           }
        }
    }


}