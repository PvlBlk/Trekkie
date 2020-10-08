package com.example.trekieapp.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import com.example.trekieapp.Error
import com.example.trekieapp.fragment.FirstFragment
import com.example.trekieapp.R
import com.example.trekieapp.viewmodel.ActivityViewModel
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.coroutines.launch


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
        activityViewModel.episodeSummaryLiveData.observe(this, Observer {
            var newList : List<String>
            it.forEach {
              Toast.makeText(this, it.vote_average.toString(), LENGTH_SHORT).show()
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