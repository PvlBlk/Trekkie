package com.example.trekieapp

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.JsonElement
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.button_second
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.fragment_second.*
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private lateinit var activityViewModel: ActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityViewModel = ViewModelProviders.of(this).get(ActivityViewModel::class.java)
        setContentView(R.layout.activity_main)
        buttonOneClickListener()
        observeGetPosts()
    }
    private fun observeGetPosts() {
        activityViewModel.universalLiveData.observe(this, Observer {
            var text : String? = it.body().toString()
            textView_first.text = text
        })
    }
    private fun buttonOneClickListener() {
        button_second.setOnClickListener {
            lifecycleScope.launch {
                activityViewModel.getUsersWithLiveData(edit_query.text.toString())
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