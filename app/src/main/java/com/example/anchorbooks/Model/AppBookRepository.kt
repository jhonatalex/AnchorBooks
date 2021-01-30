package com.example.anchorbooks.Model

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.anchorbooks.Model.Local.DaoBook
import com.example.anchorbooks.Model.Local.DetailBook
import com.example.anchorbooks.Model.Remote.RetrofitApiClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AppBookRepository(private val myDao: DaoBook) {


    private val myRetrofit= RetrofitApiClient.retrofitInstance()

    val listBooks= myDao.getAllBooks()


    fun getBooksDetail(id: Int): LiveData<DetailBook> {

        return myDao.getOneBookDetails(id)
    }



    fun getBookFromApi()= CoroutineScope(Dispatchers.IO).launch{

        val service= kotlin.runCatching { myRetrofit.fetchAllBooks() }
        service.onSuccess {
            when(it.code()) {
                in 200..299 -> it.body()?.let {

                    myDao.insertAllBooks(it)
                }

                in 300..599 -> Log.e("ERROR", it.errorBody().toString())
                else -> Log.d("ERROR", it.errorBody().toString())
            }
        }

        service.onFailure {
            Log.e("ERROR", it.message.toString())

        }

    }

    fun getDetailFromApi(id: Int)= CoroutineScope(Dispatchers.IO).launch{

        val service= kotlin.runCatching { myRetrofit.fetchOneBook(id)}
        service.onSuccess {
            when(it.code()) {
                in 200..299 -> it.body()?.let {

                    myDao.insertOneDetail(it)
                }

                in 300..599 -> Log.e("ERROR", it.errorBody().toString())
                else -> Log.d("ERROR", it.errorBody().toString())
            }
        }

        service.onFailure {
            Log.e("ERROR", it.message.toString())

        }

    }














}