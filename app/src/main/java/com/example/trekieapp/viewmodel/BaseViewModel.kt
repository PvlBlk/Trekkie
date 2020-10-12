package com.example.trekieapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.trekieapp.network.MovieApi
import com.example.trekieapp.network.Stapi
import com.example.trekieapp.network.NetworkService

abstract class BaseViewModel : ViewModel() {

    var stapi: Stapi = NetworkService.stapiRetrofitService()
    var movieApi: MovieApi = NetworkService.movieRetrofitService()
}
