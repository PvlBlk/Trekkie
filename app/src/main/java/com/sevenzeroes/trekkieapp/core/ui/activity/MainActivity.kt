package com.sevenzeroes.trekkieapp.core.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sevenzeroes.trekkieapp.R
import com.sevenzeroes.trekkieapp.core.ui.helpers.MainViewPagerAdapter
import com.sevenzeroes.trekkieapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity()  {

    private var _binding: ActivityMainBinding? = null

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewPager()
        initBnvListener()
    }

    private fun setupViewPager(){
        binding.vpMain.adapter = MainViewPagerAdapter(this)
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

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}