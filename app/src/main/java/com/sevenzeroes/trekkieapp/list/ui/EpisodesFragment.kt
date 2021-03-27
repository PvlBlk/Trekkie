package com.sevenzeroes.trekkieapp.list.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sevenzeroes.trekkieapp.databinding.EpisodesFragmentBinding
import com.sevenzeroes.trekkieapp.list.data.EpisodeSummary
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class EpisodesFragment : Fragment() {

    companion object {
        fun newInstance() = EpisodesFragment()
    }

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
        GlobalScope.launch {
        episodesViewModel.getEpisodesWithLiveData("Realm of")
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