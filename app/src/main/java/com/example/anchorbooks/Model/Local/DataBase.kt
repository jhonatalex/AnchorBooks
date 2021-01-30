package com.example.anchorbooks.Model.Local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Book::class,DetailBook::class],version=1)
abstract class DataBase: RoomDatabase() {

    abstract fun daoBook(): DaoBook

    companion object {

        // Singleton prevents multiple instances of database opening at the

        @Volatile
        private var INSTANCE: DataBase? = null

        fun getDatabase(context: Context): DataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DataBase::class.java,
                    "ANCHORBOOKS_databaseV1"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }


}