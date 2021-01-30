package com.example.anchorbooks.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.anchorbooks.Model.AppBookRepository
import com.example.anchorbooks.Model.Local.Book
import com.example.anchorbooks.Model.Local.DataBase
import com.example.anchorbooks.Model.Local.DetailBook

class AppViewModel(application: Application): AndroidViewModel(application) {

    private val myRepository:AppBookRepository

    val election= MutableLiveData<Int>()

    val viewListBooks: LiveData<List<Book>>


    init {
        val nDao= DataBase.getDatabase(application).daoBook()
        myRepository=  AppBookRepository(nDao)

        viewListBooks=myRepository.listBooks
        myRepository.getBookFromApi()
    }


    fun getBookDetail(id: Int): LiveData<DetailBook> {
        myRepository.getDetailFromApi(id)
        return myRepository.getBooksDetail(id)

    }

    fun  bookDetailSelect  (id: Int){
        election.value=id
    }


}