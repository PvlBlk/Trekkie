package com.example.trekieapp

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.google.gson.JsonElement
import kotlinx.android.synthetic.main.content_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ActivityViewModel : BaseViewModel() {

    // Создаем лайвдату для нашего списка юзеров
    val simpleLiveData = MutableLiveData<Event<Users>>()

    // Получение юзеров. Обращаемся к функции  requestWithLiveData
    // из BaseViewModel передаем нашу лайвдату и говорим,
    // какой сетевой запрос нужно выполнить и с какими параметрами
    // В данном случае это api.getUsers
    // Теперь функция сама выполнит запрос и засетит нужные
    // данные в лайвдату

    /*
        fun getUsers(page: String) {
            requestWithLiveData(simpleLiveData) {
                api.getUsers(
                    page = page
                )

            }
        }
    */
    fun getUsers  (text : String, applicationContext : Context) : Response<Users>? {
        var response1 : Response<Users>? = null
        var api = NetworkService.retrofitService()
        var call = api.getUsers(text)
        call.enqueue(object : Callback<Users> {
            override fun onResponse(call: Call<Users>, response: Response<Users>) {
                Log.d("MainActivity::","onResponse:" + response.body())
                Toast.makeText(applicationContext, "" + response.body(), Toast.LENGTH_LONG).show()
                response1 = response
            }
            override fun onFailure(call: Call<Users>, t: Throwable) {
                Log.d("MainActivity::","onFailure:")
            }
        })
        return  response1
    }

    // Здесь аналогично, но вместо лайвдаты используем котлиновский колбек
    // UPD Полученный результат мы можем обработать здесь перед отправкой во вью
    fun getUsersError(page: Int, callback: (data: Event<Users>) -> Unit) {
        requestWithCallback({
            api.getUsersError(
                page = page
            )
        }) {
            callback(it)
        }
    }
}