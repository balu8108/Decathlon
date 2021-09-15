package com.bala.nytnews.ui.main.utils

import com.bala.nytnews.ui.main.data.NewsItem
import org.json.JSONObject

object NewsItemJsonParser {

    const val curlyApostropheCode = 8217

    fun parseNewsItem(jsonObject: JSONObject): NewsItem {
        val lTitle = jsonObject.getJSONObject("headline").getString("main")/*.replace(
            curlyApostropheCode.toChar(), '\''
        )*/
        val lSnippet = jsonObject.getString("abstract")/*.replace(
            curlyApostropheCode.toChar(), '\''
        )*/
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