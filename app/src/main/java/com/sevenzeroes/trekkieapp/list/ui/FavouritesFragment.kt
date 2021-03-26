package com.sevenzeroes.trekkieapp.list.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sevenzeroes.trekkieapp.R
import com.sevenzeroes.trekkieapp.databinding.FavouritesFragmentBinding

class FavouritesFragment : Fragment() {

    companion object {
        fun newInstance() = FavouritesFragment()
    }

    private lateinit var viewModel: FavouritesViewModel
    private lateinit var binding: FavouritesFragmentBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FavouritesFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FavouritesViewModel::class.java)

    }

}