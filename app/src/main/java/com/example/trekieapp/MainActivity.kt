package com.example.trekieapp

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.JsonElement
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.fragment_first.*
import kotlinx.android.synthetic.main.fragment_second.*
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity()  {

    private lateinit var activityViewModel: ActivityViewModel
    lateinit var fragment: FirstFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityViewModel = ViewModelProviders.of(this).get(ActivityViewModel::class.java)
        setContentView(R.layout.activity_main)
        buttonOneClickListener()
        observeGetPosts()
    }
    private fun observeGetPosts() {
        activityViewModel.episodesLiveData.observe(this, Observer {
            var newList : List<String>
            it.forEach {
                Toast.makeText(this, it.title, LENGTH_SHORT).show()
            }
        })
    }
    private fun buttonOneClickListener() {
        searchBtn.setOnClickListener {
            lifecycleScope.launch {
              activityViewModel.getEpisodesWithLiveData(searchField.text.toString())
            }
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