package com.sevenzeroes.trekkieapp.core.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.sevenzeroes.trekkieapp.core.domain.models.EpisodeSummary
import com.sevenzeroes.trekkieapp.core.helpers.Status
import com.sevenzeroes.trekkieapp.core.ui.helpers.EpisodesAdapter
import com.sevenzeroes.trekkieapp.core.ui.viewModels.FavouritesViewModel
import com.sevenzeroes.trekkieapp.databinding.FavouritesFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import es.dmoral.toasty.Toasty
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavouritesFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {


    private val favouritesViewModel: FavouritesViewModel by viewModels()

    private var _binding: FavouritesFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: EpisodesAdapter

    var episodes = mutableListOf<EpisodeSummary>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FavouritesFragmentBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupFavoritesList()
        observeEpisodes()
        initSwipeListener()
        getAllEpisodesFromDb()
    }

    private fun setupFavoritesList(){
        adapter = EpisodesAdapter(::toggleFavorite)
        binding.rvFavoritesList.adapter = adapter
    }

    private fun toggleFavorite(episodeSummary: EpisodeSummary) {
        favouritesViewModel.toggleFavorite(episodeSummary)
    }

    private fun observeEpisodes(){
        favouritesViewModel.favoriteEpisodes.observe(viewLifecycleOwner, {
            when (it.status){
                Status.SUCCESS -> {
                    binding.srlFavorites.isRefreshing = false
                    if (it.data!=null)
                        adapter.setData(it.data)
                }
                Status.LOADING -> {
                    binding.srlFavorites.isRefreshing = true
                }
                Status.ERROR ->{
                    binding.srlFavorites.isRefreshing = false
                    Toasty.error(requireContext(), it.status.name).show()
                }
            }
        })
    }

    private fun initSwipeListener(){
        binding.srlFavorites.setOnRefreshListener(this)
    }

    private fun getAllEpisodesFromDb(){
        GlobalScope.launch(Dispatchers.IO) {
           favouritesViewModel.getAllEpisodesFromDb()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onRefresh() {
        getAllEpisodesFromDb()
    }
}