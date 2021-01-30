package com.example.anchorbooks.Model.Local


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName= "book")
data class Book(
    @SerializedName("author")
    val author: String,
    @SerializedName("country")
    val country: String,
    @PrimaryKey @SerializedName("id")
    val id: Int,
    @SerializedName("imageLink")
    val imageLink: String,
    @SerializedName("language")
    val language: String,
    @SerializedName("title")
    val title: String
)