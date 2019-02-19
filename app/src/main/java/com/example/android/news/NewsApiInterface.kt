package com.example.android.news

import models.News
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by HP on 2/6/2019.
 */

interface NewsApiInterface {

    @GET("search?show-fields=headline,shortUrl,thumbnail,byline")
    fun getNews(@Query("api-key") apiKey: String,
                @Query("section") section: String): Call<News>
}
