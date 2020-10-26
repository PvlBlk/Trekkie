package com.example.trekkieapp.activity

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
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.droidman.ktoasty.KToasty
import com.example.trekkieapp.MyEpisodeRecyclerViewAdapter
import com.example.trekkieapp.R
import com.example.trekkieapp.model.EpisodeSummary
import com.example.trekkieapp.viewmodel.ActivityViewModel
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
        progress_bar.visibility = View.GONE
        recyclerView.visibility = View.VISIBLE
        observeIsLoading()
        observeGetPosts()
    }

    private fun observeIsLoading() {
        activityViewModel.isLoading.observe(this, Observer {
            if (it){
                progress_bar.visibility = View.VISIBLE
                recyclerView.visibility = View.GONE
            }
            else {
                progress_bar.visibility = View.GONE
                recyclerView.visibility = View.VISIBLE
            }
        })
    }

    private fun observeGetPosts() {
        activityViewModel.episodeSummaryLiveData.observe(this, Observer {
           fillRecyclerView(it)
        })
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