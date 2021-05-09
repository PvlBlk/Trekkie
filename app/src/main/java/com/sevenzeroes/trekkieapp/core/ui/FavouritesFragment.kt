package com.sevenzeroes.trekkieapp.core.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sevenzeroes.trekkieapp.databinding.FavouritesFragmentBinding

class FavouritesFragment : Fragment() {


    private lateinit var viewModel: FavouritesViewModel
    private lateinit var binding: FavouritesFragmentBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FavouritesFragmentBinding.inflate(layoutInflater, container, false)
        viewModel = ViewModelProvider(this).get(FavouritesViewModel::class.java)

        return binding.root
    }



}