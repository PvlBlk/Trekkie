package com.example.trekieapp.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.activity.viewModels

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trekieapp.Error
import com.example.trekieapp.MyEpisodeRecyclerViewAdapter
import com.example.trekieapp.R
import com.example.trekieapp.model.EpisodeSummary
import com.example.trekieapp.viewmodel.ActivityViewModel
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity()  {

    private lateinit var activityViewModel: ActivityViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityViewModel = ViewModelProviders.of(this).get(ActivityViewModel::class.java)
        setContentView(R.layout.activity_main)
        buttonOneClickListener()
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
    private fun buttonOneClickListener() {
        searchBtn.setOnClickListener {
            lifecycleScope.launch {
              activityViewModel.getEpisodesWithLiveData(searchField.text.toString())
            }
        }
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

        private fun viewTwoLoading() {}

    private fun viewTwoSuccess() {}

    private fun viewTwoError(error: Error?) {
        error?.let {
            Toast.makeText(applicationContext, error.errorMsg, Toast.LENGTH_SHORT).show()
        }
    }
}