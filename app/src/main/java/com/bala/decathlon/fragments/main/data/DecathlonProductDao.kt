package com.bala.decathlon.fragments.main.data

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DecathlonProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(decathlonProducts: List<DecathlonProduct>)

    @Query("SELECT * FROM decathlonproduct WHERE name LIKE '%' || :queryFilter || '%' OR brand LIKE '%' || :queryFilter || '%'")
    fun getAllDecathlonProducts(queryFilter: String): PagingSource<Int, DecathlonProduct>

    @Query("SELECT * FROM decathlonproduct WHERE name LIKE '%' || :queryFilter || '%' OR brand LIKE '%' || :queryFilter || '%' ORDER BY name")
    fun getAllDecathlonProductsSortedByName(queryFilter: String): PagingSource<Int, DecathlonProduct>

    @Query("SELECT * FROM decathlonproduct WHERE name LIKE '%' || :queryFilter || '%' OR brand LIKE '%' || :queryFilter || '%' ORDER BY price")
    fun getAllDecathlonProductsSortedByPrice(queryFilter: String): PagingSource<Int, DecathlonProduct>

    @Query("DELETE FROM decathlonproduct")
    suspend fun clearAllDecathlonProducts()
}