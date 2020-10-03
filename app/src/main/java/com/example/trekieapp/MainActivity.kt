package com.example.trekieapp

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.JsonElement
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
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
    }

    private fun buttonOneClickListener() {
        button_first.setOnClickListener {
            var response: Response<Episode>? = activityViewModel.getUsers(
                text = edit_query.text.toString(),
                applicationContext = applicationContext
            )
        }
    }
}