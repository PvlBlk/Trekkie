package com.example.trekieapp

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    var api: Api = NetworkService.retrofitService()

    // У нас будут две базовые функции requestWithLiveData и
    // requestWithCallback, в зависимости от ситуации мы будем
    // передавать в них лайвдату или колбек вместе с параметрами сетевого
    // запроса. Функция принимает в виде параметра ретрофитовский suspend запрос,
    // проверяет на наличие ошибок и сетит данные в виде ивента либо в
    // лайвдату либо в колбек. Про ивент будет написано ниже

    fun <T> requestWithLiveData(
        liveData: MutableLiveData<Event<T>>,
        request: suspend () -> ResponseWrapper<T>) {
        // В начале запроса сразу отправляем ивент загрузки
        liveData.postValue(Event.loading())

        // Привязываемся к жизненному циклу ViewModel, используя viewModelScope.
        // После ее уничтожения все выполняющиеся длинные запросы
        // будут остановлены за ненадобностью.
        // Переходим в IO поток и стартуем запрос
        Log.d("BaseViewModel::", "test" )
        this.viewModelScope.launch(Dispatchers.IO) {
            Log.d("BaseViewModel::", "launch")
            try {
                val response = request.invoke()
                Log.d("BaseViewModel::", "try" + response.data)
                if (response.data != null) {
                    // Сетим в лайвдату командой postValue в IO потоке
                    liveData.postValue(Event.success(response.data))
                } else if (response.error != null) {
                    liveData.postValue(Event.error(response.error))
                }
            } catch (e: Exception) {
                Log.d("BaseViewModel::", "catch")
                e.printStackTrace()
                liveData.postValue(Event.error(null))
            }
        }
    }

    fun <T> requestWithCallback(
        request: suspend () -> ResponseWrapper<T>,
        response: (Event<T>) -> Unit) {

        response(Event.loading())

        this.viewModelScope.launch(Dispatchers.IO) {
            try {
                val res = request.invoke()

                // здесь все аналогично, но полученные данные
                // сетим в колбек уже в главном потоке, чтобы
                // избежать конфликтов с
                // последующим использованием данных
                // в context классах
                launch(Dispatchers.Main) {
                    if (res.data != null) {
                        response(Event.success(res.data))
                    } else if (res.error != null) {
                        response(Event.error(res.error))
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                // UPD (подсказали в комментариях) В блоке catch ивент передаем тоже в Main потоке
                launch(Dispatchers.Main) {
                    response(Event.error(null))
                }
            }
        }
    }
}
