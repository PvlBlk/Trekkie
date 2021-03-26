package com.sevenzeroes.trekkieapp.list.ui

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.droidman.ktoasty.KToasty
import com.facebook.shimmer.ShimmerFrameLayout
import com.sevenzeroes.trekkieapp.R
import com.sevenzeroes.trekkieapp.databinding.ActivityMainBinding
import com.sevenzeroes.trekkieapp.databinding.ContentMainBinding
import com.sevenzeroes.trekkieapp.databinding.EpisodesFragmentBinding
import com.sevenzeroes.trekkieapp.list.data.EpisodeSummary
import com.sevenzeroes.trekkieapp.utils.hide
import com.sevenzeroes.trekkieapp.utils.show
import kotlinx.coroutines.launch

class EpisodesFragment : Fragment() {

    companion object {
        fun newInstance() = EpisodesFragment()
    }

    private lateinit var viewModel: EpisodesViewModel
    private lateinit var binding: EpisodesFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = EpisodesFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(EpisodesViewModel::class.java)
        // TODO: Use the ViewModel
    }



/*
    class MainActivity : AppCompatActivity()  {

        private lateinit var activityViewModel: ActivityViewModel
        private lateinit var binding: ActivityMainBinding
        private lateinit var contentMainBinding: ContentMainBinding
        private lateinit var rvEpisodeList: RecyclerView
        private lateinit var slEpisodeList: ShimmerFrameLayout
        private lateinit var tvPrompt: TextView
        private lateinit var toolbar: androidx.appcompat.widget.Toolbar


        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            activityViewModel = ViewModelProviders.of(this).get(ActivityViewModel::class.java)
            binding = ActivityMainBinding.inflate(layoutInflater)
            contentMainBinding = ContentMainBinding.inflate(layoutInflater)
            val view = binding.root
            setContentView(view)
            configureViews()
            observeIsLoading()
            observeGetPosts()
        }

        private fun configureViews(){
            rvEpisodeList = contentMainBinding.rvEpisodeList
            slEpisodeList = contentMainBinding.slEpisodeList
            tvPrompt = contentMainBinding.tvPrompt
            toolbar = binding.toolbar
            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayShowTitleEnabled(false)
            slEpisodeList.hide()
            rvEpisodeList.show()
            tvPrompt.show()
            rvEpisodeList.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL)) //good stuff

        }


        private fun observeIsLoading() {
            activityViewModel.isLoading.observe(this, Observer {
                if (it){
                    slEpisodeList.show()
                    slEpisodeList.startShimmer()
                    rvEpisodeList.hide()
                    togglePromptVisibility(false)
                }
                else {
                    slEpisodeList.stopShimmer()
                    slEpisodeList.hide()
                    rvEpisodeList.show()
                }
            })
        }

        private fun observeGetPosts() {
            activityViewModel.episodeSummaryLiveData.observe(this, Observer {
                if (it.isEmpty()) {
                    togglePromptVisibility(true)
                }
                else {
                    fillRecyclerView(it)
                    togglePromptVisibility(false)
                }
            })
        }
        private fun togglePromptVisibility(visibility : Boolean){
            if (visibility) {
                tvPrompt.visibility = View.VISIBLE
            } else {
                tvPrompt.visibility = View.GONE
            }
        }
        private fun fillRecyclerView(list: List<EpisodeSummary>) {
            Log.d("fill::", "" + list.toString())
            clearRecyclerView()
            rvEpisodeList.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = MyEpisodeRecyclerViewAdapter(list, this@MainActivity)

            }
        }
        private fun clearRecyclerView() {
            var list = mutableListOf<EpisodeSummary>()
            list.removeAll(list)
            rvEpisodeList.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = MyEpisodeRecyclerViewAdapter(list, this@MainActivity)
            }
        }

        override fun onCreateOptionsMenu(menu: Menu): Boolean {
            menuInflater.inflate(R.menu.options_menu, menu)
            val search = menu.findItem(R.id.action_search)
            val searchView = search.actionView as SearchView
            searchView.queryHint = getString(R.string.search_hint)
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    if (isOnline()){
                        lifecycleScope.launch {
                            activityViewModel.getEpisodesWithLiveData(query)
                        }
                        return true
                    } else
                        KToasty.warning(this@MainActivity, getString(R.string.no_connection), Toast.LENGTH_SHORT, true).show()
                    return true
                }
                override fun onQueryTextChange(newText: String?): Boolean {
                    return false
                }
            })
            return true
        }

        fun isOnline(): Boolean {
            val connMgr = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo: NetworkInfo? = connMgr.activeNetworkInfo
            return networkInfo?.isConnected == true
        }

    }*/
}