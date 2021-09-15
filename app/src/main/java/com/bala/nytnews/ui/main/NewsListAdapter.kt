package com.bala.nytnews.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bala.nytnews.R
import com.bala.nytnews.databinding.NewsItemGridLayoutBinding
import com.bala.nytnews.databinding.NewsItemLayoutBinding
import com.bala.nytnews.ui.main.data.NewsItem
import com.bumptech.glide.Glide

class NewsListAdapter(
    private val context: Context,
    private val isGrid: Boolean,
    val listener: (String) -> Unit
) :
    PagingDataAdapter<NewsItem, RecyclerView.ViewHolder>(DiffCallBackNewsItems()) {

    inner class NewsItemViewHolder(val viewBinding: NewsItemLayoutBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {
        init {
            viewBinding.newItemContainer.setOnClickListener {
                getItem(bindingAdapterPosition)?.let { lNewsItem -> listener(lNewsItem.webUrl) }
            }
        }
    }

    inner class NewsItemGridViewHolder(val viewBinding: NewsItemGridLayoutBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {
        init {
            viewBinding.newItemContainer.setOnClickListener {
                getItem(bindingAdapterPosition)?.let { lNewsItem -> listener(lNewsItem.webUrl) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (isGrid) {
            return NewsItemGridViewHolder(
                NewsItemGridLayoutBinding.inflate(
                    LayoutInflater.from(context),
                    parent,
                    false
                )
            )
        }
        return NewsItemViewHolder(
            NewsItemLayoutBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is NewsItemViewHolder) {
            holder.viewBinding.apply {
                val lNewsItem = getItem(position)
                lNewsItem?.let {
                    holder.viewBinding.apply {
                        Glide.with(context)
                            .load(lNewsItem.imageUrl)
                            .placeholder(R.drawable.news_item_place_holder)
                            .into(titleImage)
                        title.text = lNewsItem.title
                        snippet.text = lNewsItem.snippet
                        date.text = lNewsItem.date
                    }
                }

            }
        } else if (holder is NewsItemGridViewHolder) {
            holder.viewBinding.apply {
                val lNewsItem = getItem(position)
                lNewsItem?.let {
                    holder.viewBinding.apply {
                        Glide.with(context)
                            .load(lNewsItem.imageUrl)
                            .placeholder(R.drawable.news_item_place_holder)
                            .into(titleImage)
                        title.text = lNewsItem.title
                        snippet.text = lNewsItem.snippet
                        date.text = lNewsItem.date
                    }
                }

            }
        }
    }


    class DiffCallBackNewsItems : DiffUtil.ItemCallback<NewsItem>() {
        override fun areItemsTheSame(oldItem: NewsItem, newItem: NewsItem) =
            oldItem.title == newItem.title

        override fun areContentsTheSame(oldItem: NewsItem, newItem: NewsItem) =
            oldItem.title == newItem.title
    }
}