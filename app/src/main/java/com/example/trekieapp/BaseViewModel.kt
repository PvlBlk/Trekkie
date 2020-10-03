package com.example.trekieapp

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trekieapp.network.Api
import com.example.trekieapp.network.NetworkService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    var api: Api = NetworkService.retrofitService()

}
