package com.sevenzeroes.trekkieapp.core.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.arlib.floatingsearchview.FloatingSearchView
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion
import com.sevenzeroes.trekkieapp.core.domain.models.EpisodeSummary
import com.sevenzeroes.trekkieapp.core.helpers.Status
import com.sevenzeroes.trekkieapp.core.helpers.isValidSearchQuery
import com.sevenzeroes.trekkieapp.core.ui.helpers.EpisodesAdapter
import com.sevenzeroes.trekkieapp.core.ui.viewModels.EpisodesViewModel
import com.sevenzeroes.trekkieapp.databinding.EpisodesFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import es.dmoral.toasty.Toasty
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class EpisodesFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener, FloatingSearchView.OnSearchListener {

    private val episodesViewModel: EpisodesViewModel by viewModels()

    private var _binding: EpisodesFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: EpisodesAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = EpisodesFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupEpisodesList()
        observeEpisodes()
        initSwipeListener()
        initSearchListener()

    }

    private fun setupEpisodesList(){
        adapter = EpisodesAdapter(::toggleFavorite)
        binding.rvEpisodeList.adapter = adapter
    }

    private fun toggleFavorite(episodeSummary: EpisodeSummary) {
        episodesViewModel.toggleFavorite(episodeSummary)
    }

    private fun observeEpisodes(){
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED)  {
                episodesViewModel.episodes.collect {
                    when (it.status){
                        Status.SUCCESS -> {
                            binding.srlEpisodes.isRefreshing = false
                            if (it.data!=null)
                                adapter.setData(it.data)
                        }
                        Status.LOADING -> {
                            binding.srlEpisodes.isRefreshing = true
                        }
                        Status.ERROR ->{
                            binding.srlEpisodes.isRefreshing = false
                        }
                    }
                }
            }
        }
    }

    private fun initSwipeListener(){
        binding.srlEpisodes.setOnRefreshListener(this)
    }

    private fun initSearchListener(){
        binding.svEpisodes.setOnSearchListener(this)
    }

    override fun onRefresh() {
        viewLifecycleOwner.lifecycleScope.launch {
            episodesViewModel.getSummaries(binding.svEpisodes.query)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onSuggestionClicked(searchSuggestion: SearchSuggestion?) {

    }

    override fun onSearchAction(currentQuery: String?) {
        if (currentQuery.isValidSearchQuery())
        viewLifecycleOwner.lifecycleScope.launch {
            episodesViewModel.getSummaries(currentQuery)
        }
    }
}