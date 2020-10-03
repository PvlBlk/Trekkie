package com.example.trekieapp

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.trekieapp.network.NetworkService
import com.google.gson.JsonElement
import kotlinx.android.synthetic.main.content_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ActivityViewModel : BaseViewModel() {

    val simpleLiveData = MutableLiveData<Call<Episode>>()

    fun getUsers  (text : String, applicationContext : Context) : Response<Episode>? {
        var response1 : Response<Episode>? = null
        var api = NetworkService.retrofitService()
        var call = api.getEpisodes(text)
        call.enqueue(object : Callback<Episode> {
            override fun onResponse(call: Call<Episode>, response: Response<Episode>) {
                Log.d("MainActivity::","onResponse:" + response.body())
                Toast.makeText(applicationContext, "" + response.body(), Toast.LENGTH_LONG).show()
                response1 = response
            }
            override fun onFailure(call: Call<Episode>, t: Throwable) {
                Log.d("MainActivity::","onFailure:")
            }
        })
        return  response1
    }


}