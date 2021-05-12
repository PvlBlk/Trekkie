package com.sevenzeroes.trekkieapp.core.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.sevenzeroes.trekkieapp.core.domain.models.EpisodeSummary
import com.sevenzeroes.trekkieapp.databinding.FavouritesFragmentBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavouritesFragment : Fragment() {


    private val favouritesViewModel: FavouritesViewModel by viewModels()
    private var _binding: FavouritesFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: EpisodesRecyclerViewAdapter

    var episodes = mutableListOf<EpisodeSummary>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FavouritesFragmentBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupFavoritesList()
        fillFavorites()
    }

    private fun setupFavoritesList(){
        adapter = EpisodesRecyclerViewAdapter(favouritesViewModel)
        binding.rvFavoritesList.adapter = adapter
    }

    private fun fillFavorites(){
        GlobalScope.launch(Dispatchers.IO) {
            episodes = favouritesViewModel.getAllEpisodesFromDb?.invoke() as MutableList<EpisodeSummary>
            withContext(Dispatchers.Main) {
                adapter.setData(episodes)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}