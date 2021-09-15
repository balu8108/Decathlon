package com.bala.nytnews.ui.main.utils

import com.bala.nytnews.ui.main.data.NewsItem
import org.json.JSONObject

object NewsItemJsonParser {

    fun parseNewsItem(jsonObject: JSONObject): NewsItem {
        val lTitle = jsonObject.getJSONObject("headline").getString("main").replace(
            "â\u0080\u0099", "'", true
        ).replace(
            "â\u0080\u0098", "'", true
        )
        val lSnippet = jsonObject.getString("abstract").replace(
            "â\u0080\u0099", "'", true
        ).replace(
            "â\u0080\u0098", "'", true
        )
        val lDate = jsonObject.getString("pub_date").slice(0 until 10)
        var lImageUrl = ""
        if (jsonObject.getJSONArray("multimedia").length() > 0)
            lImageUrl =
                "https://www.nytimes.com/" + jsonObject.getJSONArray("multimedia").getJSONObject(0)
                    .getString("url")
        val lWebUrl = jsonObject.getString("web_url")

        return NewsItem(lTitle, lSnippet, lDate, lImageUrl, lWebUrl)
    }
}