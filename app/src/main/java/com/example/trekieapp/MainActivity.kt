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
        setContentView(R.layout.activity_main)

        activityViewModel = ViewModelProviders.of(this).get(ActivityViewModel::class.java)
        observeGetPosts()

        buttonOneClickListener()
        buttonTwoClickListener()
    }

    // Наблюдаем за нашей лайвдатой
    // В зависимости от Ивента устанавливаем нужное состояние вью
    private fun observeGetPosts() {
        activityViewModel.simpleLiveData.observe(this, Observer {
            viewOneSuccess(it.data)
            when (it.status) {
                Status.LOADING -> viewOneLoading()
                Status.SUCCESS -> viewOneSuccess(it.data)
                Status.ERROR -> viewOneError(it.error)
            }
        })
    }


    private fun buttonOneClickListener() {
        button_first.setOnClickListener {
            // activityViewModel.getUsers(page = "Best of both" )
            var response : Response<Users>? = activityViewModel.getUsers(text = edit_query.text.toString(), applicationContext = applicationContext)
        }
    }

    // Здесь так же наблюдаем за Ивентом, используя колбек
    private fun buttonTwoClickListener() {
        button_second.setOnClickListener {
            activityViewModel.getUsersError(page = 2) {
                when (it.status) {
                    Status.LOADING -> viewTwoLoading()
                    Status.SUCCESS -> viewTwoSuccess(it.data)
                    Status.ERROR -> viewTwoError(it.error)
                }
            }
        }
    }

    private fun viewOneLoading() {
        // Пошла загрузка, меняем состояние вьюх
    }

    private fun viewOneSuccess(data: Users?) {
        val usersList: MutableList<Users.Item>? = data?.items as MutableList<Users.Item>?
        usersList?.shuffle()
        Toast.makeText(applicationContext, "" + usersList.toString(), Toast.LENGTH_SHORT).show()
        usersList?.let {
            Toast.makeText(applicationContext, "${it}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun viewOneError(error: Error?) {
        // Показываем ошибку
    }

    private fun viewTwoLoading() {}

    private fun viewTwoSuccess(data: Users?) {}

    private fun viewTwoError(error: Error?) {
        error?.let {
            Toast.makeText(applicationContext, error.errorMsg, Toast.LENGTH_SHORT).show()
        }
    }
}