package com.example.android.news

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView

import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target

import java.util.ArrayList

import models.Article

/**
 * Created by HP on 2/6/2019.
 */

class NewsAdapter(private val articles: List<Article>, private var context: Context?) : RecyclerView.Adapter<NewsAdapter.MyViewHolder>() {
    private lateinit var rootView: View

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        rootView = LayoutInflater.from(parent.context)
                .inflate(R.layout.single_item, parent, false)
        context = parent.context
        return MyViewHolder(rootView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentArticle = articles[position]
        Glide.with(context!!)
                .load(currentArticle.field!!.imgUrl)
                .apply(RequestOptions().centerCrop())
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(e: GlideException?, model: Any, target: Target<Drawable>, isFirstResource: Boolean): Boolean {
                        holder.progressBar.visibility = View.GONE
                        return false
                    }

                    override fun onResourceReady(resource: Drawable, model: Any, target: Target<Drawable>, dataSource: DataSource, isFirstResource: Boolean): Boolean {
                        holder.progressBar.visibility = View.GONE
                        return false
                    }
                })
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(holder.imageView)
        holder.webTitle_tv.text = currentArticle.field!!.newsTitle
        holder.authorName_tv.text = currentArticle.field!!.authorName
        holder.date_tv.setText(currentArticle.date!!.substring(0, 10))
        holder.sectionName_tv.text = currentArticle.sectionName

        rootView!!.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(currentArticle.field!!.webUrl)
            context!!.startActivity(i)
        }

    }

    override fun getItemCount(): Int {
        return articles.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var progressBar: ProgressBar
        internal var imageView: ImageView
        internal var webTitle_tv: TextView
        internal var authorName_tv: TextView
        internal var date_tv: TextView
        internal var sectionName_tv: TextView

        init {
            progressBar = itemView.findViewById(R.id.loadimg_pb)
            imageView = itemView.findViewById(R.id.img)
            webTitle_tv = itemView.findViewById(R.id.headline_tv)
            authorName_tv = itemView.findViewById(R.id.byline_tv)
            date_tv = itemView.findViewById(R.id.webPublicationDate_tv)
            sectionName_tv = itemView.findViewById(R.id.sectionName_tv)
        }
    }
}
