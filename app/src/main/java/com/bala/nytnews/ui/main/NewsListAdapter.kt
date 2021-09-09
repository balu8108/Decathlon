package com.bala.nytnews.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bala.nytnews.databinding.NewsItemLayoutBinding
import com.bala.nytnews.ui.main.data.NewsItem
import com.bumptech.glide.Glide

class NewsListAdapter(private val context: Context) :
    ListAdapter<NewsItem, NewsListAdapter.NewsItemViewHolder>(DiffCallBackNewsItems()) {

    inner class NewsItemViewHolder(val viewBinding: NewsItemLayoutBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {
        /*init {
            viewBinding.root.setOnClickListener {

            }
        }*/
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsItemViewHolder {
        return NewsItemViewHolder(
            NewsItemLayoutBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NewsItemViewHolder, position: Int) {
        /*holder.viewBinding.apply {
            val lNewsItem = getItem(position)
            holder.viewBinding.apply {
                *//*Glide.with(context)
                    .load(lNewsItem.imageUrl)
                    .into(titleImage)
                title.text = lNewsItem.title
                snippet.text = lNewsItem.snippet
                date.text = lNewsItem.date*//*
            }
        }*/
    }


    class DiffCallBackNewsItems : DiffUtil.ItemCallback<NewsItem>() {
        override fun areItemsTheSame(oldItem: NewsItem, newItem: NewsItem) = false
        /*oldItem.title == newItem.title*/

        override fun areContentsTheSame(oldItem: NewsItem, newItem: NewsItem) = false
        /*oldItem.title == newItem.title*/
    }
}