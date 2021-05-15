package com.sevenzeroes.trekkieapp.core.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.sevenzeroes.trekkieapp.core.helpers.Status
import com.sevenzeroes.trekkieapp.core.ui.viewModels.EpisodesViewModel
import com.sevenzeroes.trekkieapp.databinding.EpisodesFragmentBinding
import es.dmoral.toasty.Toasty
import kotlinx.coroutines.launch

class EpisodesFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {

    private val episodesViewModel: EpisodesViewModel by viewModels()

    private var _binding: EpisodesFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: EpisodesRecyclerViewAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = EpisodesFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupEpisodesList()
        observeEpisodes()
        initSwipeListener()
        viewLifecycleOwner.lifecycleScope.launch {
            episodesViewModel.getSummaries("frame")
        }
    }

    private fun setupEpisodesList(){
        adapter = EpisodesRecyclerViewAdapter(episodesViewModel)
        binding.rvEpisodeList.adapter = adapter
    }

    private fun observeEpisodes(){
        episodesViewModel.episodes.observe(viewLifecycleOwner, {
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
                    Toasty.error(requireContext(), it.status.name).show()
                }
            }
        })
    }

    private fun initSwipeListener(){
        binding.srlEpisodes.setOnRefreshListener(this)
    }

    override fun onRefresh() {
        viewLifecycleOwner.lifecycleScope.launch {
            episodesViewModel.getSummaries("lessons")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}