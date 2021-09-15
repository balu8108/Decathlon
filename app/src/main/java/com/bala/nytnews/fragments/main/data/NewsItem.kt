package com.bala.nytnews.fragments.main.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NewsItem(
    val title: String,
    val snippet: String,
    val date: String,
    val imageUrl: String,
    val webUrl: String,
    @PrimaryKey
    val id: String = title.slice(0..6) + date
)
