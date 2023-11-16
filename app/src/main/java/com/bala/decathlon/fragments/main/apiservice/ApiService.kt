package com.bala.decathlon.fragments.main.apiservice

import com.android.volley.toolbox.HurlStack
import com.android.volley.toolbox.Volley
import com.bala.decathlon.DecathlonProductApplication
import com.bala.decathlon.fragments.main.data.DecathlonProduct
import com.bala.decathlon.fragments.main.data.SampleDecathlonProducts
import kotlinx.coroutines.delay

object ApiService {

    private val requestQueue by lazy {
        Volley.newRequestQueue(
            DecathlonProductApplication.application,
            HurlStack()
        )
    }

    suspend fun getDecathlonProductsInPage(
        page: Int
    ): List<DecathlonProduct>  {
        delay(1000)
        return SampleDecathlonProducts.getDecathlonProducts(page)
    }

}