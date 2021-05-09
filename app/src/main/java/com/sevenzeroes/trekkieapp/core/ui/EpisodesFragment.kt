package com.sevenzeroes.trekkieapp.core.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.sevenzeroes.trekkieapp.R
import com.sevenzeroes.trekkieapp.core.helpers.Event
import com.sevenzeroes.trekkieapp.core.helpers.Status
import com.sevenzeroes.trekkieapp.databinding.EpisodesFragmentBinding
import es.dmoral.toasty.Toasty
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
        episodesViewModel.getEpisodes("data")
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
                    if (it.data!=null)
                    adapter.setData(it.data)
                }
                Status.LOADING -> {
                    Toasty.info(requireContext(), getString(R.string.message_loading)).show()
                }
                Status.ERROR ->{
                    Toasty.info(requireContext(), getString(R.string.message_loading)).show()
                }
            }
        })
    }
}