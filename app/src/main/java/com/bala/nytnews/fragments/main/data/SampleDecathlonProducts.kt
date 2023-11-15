package com.bala.nytnews.fragments.main.data

import kotlin.random.Random


object SampleDecathlonProducts{
    fun getDecathlonProducts(page: Int):List<DecathlonProduct>{
        return mutableListOf<DecathlonProduct>().apply {
            repeat(20){
                this.add(DecathlonProduct(
                    name = getRandomString(8),
                    price = Random.nextFloat() * 500,
                    brand = getRandomString(4),
                    imageUrl = ""
                ))
            }
        }
    }

    fun getRandomString(length: Int) : String {
        val allowedChars = ('A'..'Z') + ('a'..'z')
        return (1..length)
            .map { allowedChars.random() }
            .joinToString("")
    }
}