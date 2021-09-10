package com.bala.nytnews.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.toolbox.HurlStack
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bala.nytnews.ui.main.data.NewsItem
import com.bala.nytnews.ui.main.utils.NewsItemJsonParser
import org.json.JSONObject

class MainViewModel(application: Application) : AndroidViewModel(application) {

    // Instantiate the RequestQueue with the cache and network. Start the queue.
    private val requestQueue by lazy { Volley.newRequestQueue(application, HurlStack()) }

    val newsItems: LiveData<MutableList<NewsItem>>
        get() = _newsItems
    private val _newsItems by lazy { MutableLiveData<MutableList<NewsItem>>(mutableListOf()) }

    fun makeNetworkRequest() {
        val lUrl =
            "https://api.nytimes.com/svc/search/v2/articlesearch.json?api-key=5763846de30d489aa867f0711e2b031c&q=india&page=0"
        val lNewsItemRequest = StringRequest(
            Request.Method.GET, lUrl,
            { response ->
                val lJsonArray = JSONObject(response).getJSONObject("response").getJSONArray("docs")
                val lNewsItems = _newsItems.value
                for (i in 0 until lJsonArray.length()) {
                    val lJsonObject = lJsonArray.getJSONObject(i)
                    val lNewsItem = NewsItemJsonParser.parseNewsItem(lJsonObject)
                    lNewsItems?.add(lNewsItem)
                }
                _newsItems.value = lNewsItems
            },
            {
                // Handle error
            })

        requestQueue.add(lNewsItemRequest)
    }
}