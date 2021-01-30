package com.example.anchorbooks.Model.Local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface DaoBook {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllBooks(list: List<Book>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOneDetail(details: DetailBook)

    @Query("SELECT * FROM book")
    fun getAllBooks(): LiveData<List<Book>>

    @Query("SELECT * FROM DetailBook WHERE id=:id")
    fun getOneBookDetails(id: Int): LiveData<DetailBook>

}