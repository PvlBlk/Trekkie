package com.sevenzeroes.trekkieapp.activity

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.droidman.ktoasty.KToasty
import com.sevenzeroes.trekkieapp.MyEpisodeRecyclerViewAdapter
import com.sevenzeroes.trekkieapp.R
import com.sevenzeroes.trekkieapp.model.EpisodeSummary
import com.sevenzeroes.trekkieapp.viewmodel.ActivityViewModel
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity()  {

    private lateinit var activityViewModel: ActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityViewModel = ViewModelProviders.of(this).get(ActivityViewModel::class.java)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayShowTitleEnabled(false)
        shimmerLayout.visibility = View.GONE
        recyclerView.visibility = View.VISIBLE
        prompt.visibility = View.VISIBLE
        recyclerView.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL)) //good stuff
        observeIsLoading()
        observeGetPosts()
    }

    private fun observeIsLoading() {
        activityViewModel.isLoading.observe(this, Observer {
            if (it){
                shimmerLayout.visibility = View.VISIBLE
                shimmerLayout.startShimmer()
                recyclerView.visibility = View.GONE
                togglePromptVisibility(false)
            }
            else {
                shimmerLayout.stopShimmer()
                shimmerLayout.visibility = View.GONE
                recyclerView.visibility = View.VISIBLE
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
            prompt.visibility = View.VISIBLE
        } else {
            prompt.visibility = View.GONE
        }
    }
    private fun fillRecyclerView(list: List<EpisodeSummary>) {
    Log.d("fill::", "" + list.toString())
    clearRecyclerView()
    recyclerView.apply {
        layoutManager = LinearLayoutManager(context)
        adapter = MyEpisodeRecyclerViewAdapter(list, this@MainActivity)

    }
}
    private fun clearRecyclerView() {
        var list = mutableListOf<EpisodeSummary>()
        list.removeAll(list)
        recyclerView.apply {
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

}