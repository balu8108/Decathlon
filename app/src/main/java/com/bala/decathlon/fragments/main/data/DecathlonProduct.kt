package com.bala.decathlon.fragments.main.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DecathlonProduct(
    val name: String,
    val price: Float,
    val imageUrl: String,
    val brand: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)
