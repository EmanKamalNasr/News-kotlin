package fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.example.android.news.NewsAdapter
import com.example.android.news.NewsApiInterface
import com.example.android.news.R

import java.util.ArrayList

import models.Article
import models.News
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by HP on 2/7/2019.
 */

class BusinessFragment : Fragment() {
    private lateinit var newsApiInterface: NewsApiInterface
    internal lateinit var retrofit: Retrofit

    private lateinit var articleList: MutableList<Article>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        articleList = ArrayList()
        retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        newsApiInterface = retrofit.create<NewsApiInterface>(NewsApiInterface::class.java!!)
        val call: Call<News>
        call = newsApiInterface!!.getNews(API_KEY, getString(R.string.sec_business))
        call.enqueue(object : Callback<News> {
            override fun onResponse(call: Call<News>, response: Response<News>) {
                if (response.isSuccessful && response.body()!!.response!!.articles != null) {
                    if (!articleList!!.isEmpty()) {
                        articleList!!.clear()
                    }
                    articleList = response.body()!!.response!!.articles as MutableList<Article>
                    val newsAdapter = NewsAdapter(articleList, context)
                    val businessRecyclerView = view.findViewById<View>(R.id.home_recyclerview) as RecyclerView
                    businessRecyclerView.layoutManager = LinearLayoutManager(this@BusinessFragment.activity)
                    businessRecyclerView.adapter = newsAdapter
                } else {
                    Toast.makeText(context, "no result", Toast.LENGTH_LONG).show()
                }

            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                Toast.makeText(context, getString(R.string.no_internet), Toast.LENGTH_LONG).show()

            }
        })

    }

    companion object {
        private val BASE_URL = "https://content.guardianapis.com/"
        private val API_KEY = "test"
    }
}
