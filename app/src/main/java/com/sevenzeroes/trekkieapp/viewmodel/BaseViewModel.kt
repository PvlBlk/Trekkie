package com.sevenzeroes.trekkieapp.viewmodel

import androidx.lifecycle.ViewModel
import com.sevenzeroes.trekkieapp.network.MovieApi
import com.sevenzeroes.trekkieapp.network.Stapi
import com.sevenzeroes.trekkieapp.network.NetworkService

abstract class BaseViewModel : ViewModel() {

    var stapi: Stapi = NetworkService.stapiRetrofitService()
    var movieApi: MovieApi = NetworkService.movieRetrofitService()
}
