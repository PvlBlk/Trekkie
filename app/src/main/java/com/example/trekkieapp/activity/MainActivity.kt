package com.example.trekkieapp.activity

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trekkieapp.Error
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

        observeGetPosts()
    }
    private fun observeGetPosts() {
        activityViewModel.episodeSummaryLiveData.observe(this, Observer {
           fillRecyclerView(it)
/*            it.forEach {
              Toast.makeText(this, it.vote_average.toString(), LENGTH_SHORT).show()
            }*/
        })
    }

private fun fillRecyclerView(list: List<EpisodeSummary>) {
    Log.d("fill::", "" + list.toString())
    clearRecyclerView()
    recyclerView.apply {
        layoutManager = LinearLayoutManager(context)
        adapter = MyEpisodeRecyclerViewAdapter(list)

    }
}
    private fun clearRecyclerView() {
        var list = mutableListOf<EpisodeSummary>()
        list.removeAll(list)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = MyEpisodeRecyclerViewAdapter(list)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)
        val search = menu.findItem(R.id.action_search)
        val searchView = search.actionView as SearchView
        searchView.queryHint = getString(R.string.search_hint)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                lifecycleScope.launch {
                    activityViewModel.getEpisodesWithLiveData(query)
                }
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
        return true
    }

    private fun viewTwoLoading() {} //TODO - Загрузка с прогрессбаром

    private fun viewTwoSuccess() {}

    private fun viewTwoError(error: Error?) {
        error?.let {
            Toast.makeText(applicationContext, error.errorMsg, Toast.LENGTH_SHORT).show()
        }
    }
}