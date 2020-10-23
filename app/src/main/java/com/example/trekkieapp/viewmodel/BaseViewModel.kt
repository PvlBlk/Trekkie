package com.example.trekkieapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.trekkieapp.network.MovieApi
import com.example.trekkieapp.network.Stapi
import com.example.trekkieapp.network.NetworkService

abstract class BaseViewModel : ViewModel() {

    var stapi: Stapi = NetworkService.stapiRetrofitService()
    var movieApi: MovieApi = NetworkService.movieRetrofitService()
}
