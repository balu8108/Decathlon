package com.bala.nytnews.fragments.main.apiservice

import com.android.volley.toolbox.HurlStack
import com.android.volley.toolbox.Volley
import com.bala.nytnews.NytApplication
import com.bala.nytnews.fragments.main.data.DecathlonProduct
import com.bala.nytnews.fragments.main.data.SampleDecathlonProducts
import kotlinx.coroutines.delay

object ApiService {

    private val requestQueue by lazy {
        Volley.newRequestQueue(
            NytApplication.application,
            HurlStack()
        )
    }

    suspend fun getNewsItemsInPage(
        page: Int
    ): List<DecathlonProduct>  {
        delay(1000)
        return SampleDecathlonProducts.getDecathlonProducts(page)
    }

}