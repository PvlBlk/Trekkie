package com.sevenzeroes.trekkieapp.list.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.sevenzeroes.trekkieapp.databinding.EpisodesFragmentBinding
import kotlinx.coroutines.launch

class EpisodesFragment : Fragment() {

    private lateinit var episodesViewModel: EpisodesViewModel
    private lateinit var binding: EpisodesFragmentBinding
    private lateinit var adapter: EpisodesRecyclerViewAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = EpisodesFragmentBinding.inflate(layoutInflater, container, false)
        episodesViewModel = ViewModelProvider(this).get(EpisodesViewModel::class.java)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupEpisodesList()
        observeEpisodes()
        viewLifecycleOwner.lifecycleScope.launch {
        episodesViewModel.getEpisodes("realm of")
        }
    }

    private fun setupEpisodesList(){
        adapter = EpisodesRecyclerViewAdapter()
        binding.rvEpisodeList.adapter = adapter
/*        val episode1 = EpisodeSummary(name = "Name", overview = "Stuff")
        val episode2 = EpisodeSummary(name = "Name", overview = "Stuff")

        adapter.setData(listOf(episode1, episode2))*/
    }

    private fun observeEpisodes(){
        episodesViewModel.episodeSummaryLiveData.observe(viewLifecycleOwner, {
            adapter.setData(it)
        })
    }
}