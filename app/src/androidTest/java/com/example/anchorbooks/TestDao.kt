package com.example.anchorbooks

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider

import com.example.anchorbooks.Model.Local.Book
import com.example.anchorbooks.Model.Local.DaoBook
import com.example.anchorbooks.Model.Local.DataBase
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TestDao {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var mBookDao: DaoBook
    lateinit var db: DataBase

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, DataBase::class.java).build()
        mBookDao = db.daoBook()
    }
    @After
    fun shutDown() {
        db.close()
    }

    @Test
    fun insertBreedList() = runBlocking {
        //given
        val bookList = listOf<Book>(Book(id=1, author="Og Madino",country = "EEUU",imageLink = "https://images.cdn3.buscalibre.com/fit-in/360x360/06/90/06900534009e449d94bdc11448097ab4.jpg",
             language="English",title="EL VENDEDOR MAS GRANDEL DEL MUNDO",  ),
            Book( id=2, author="Paola Molina",country = "España",imageLink = "https://images.cdn3.buscalibre.com/fit-in/360x360/06/90/06900534009e449d94bdc11448097ab4.jpg",
                language="Español",title="Ciudad Staelite" ))


        //when
        mBookDao.insertAllBooks(bookList)
        //then
        mBookDao.getAllBooks().observeForever {
            assertThat(it).isNotEmpty()

            assertThat(it).isEqualTo(bookList)
             assertThat(it).hasSize(2)
            assertThat(it[0].author).isEqualTo("Og Madino")
        }

    }











}