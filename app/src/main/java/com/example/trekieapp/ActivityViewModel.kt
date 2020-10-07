package com.example.trekieapp

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.trekieapp.model.Episode
import com.example.trekieapp.network.NetworkService
import com.example.trekieapp.utils.Utils
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.content_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ActivityViewModel : BaseViewModel() {

    var episodesLiveData = MutableLiveData<List<Episode>>()

    suspend fun getEpisodesWithLiveData(text : String) {
        loadEpisodes(text = text)
    }

    suspend fun loadEpisodes(text: String){

        var result = api.getEpisodes(text)
        var list = Utils.parseEpisodeJson(response = result.body())
        episodesLiveData.postValue(list)
        Log.d("result::", "" + Utils.parseEpisodeJson(response = result.body()))
    }

}