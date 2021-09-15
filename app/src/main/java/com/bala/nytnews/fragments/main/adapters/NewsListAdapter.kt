package com.bala.nytnews.fragments.main.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bala.nytnews.R
import com.bala.nytnews.databinding.NewsItemLayoutBinding
import com.bala.nytnews.fragments.main.data.NewsItem
import com.bumptech.glide.Glide

class NewsListAdapter(
    private val context: Context,
    private val isGrid: LiveData<Boolean>,
    val listener: (String) -> Unit
) : PagingDataAdapter<NewsItem, RecyclerView.ViewHolder>(DiffCallBackNewsItems()) {

    inner class NewsItemViewHolder(val viewBinding: NewsItemLayoutBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {
        init {
            viewBinding.newItemContainer.setOnClickListener {
                getItem(bindingAdapterPosition)?.let { lNewsItem -> listener(lNewsItem.webUrl) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return NewsItemViewHolder(
            NewsItemLayoutBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is NewsItemViewHolder && isGrid.value == true) {
            holder.viewBinding.apply {
                contentRight.isVisible = false
                content.isVisible = true
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
        } else if (holder is NewsItemViewHolder) {
            holder.viewBinding.apply {
                contentRight.isVisible = true
                content.isVisible = false
                val lNewsItem = getItem(position)
                lNewsItem?.let {
                    holder.viewBinding.apply {
                        Glide.with(context)
                            .load(lNewsItem.imageUrl)
                            .placeholder(R.drawable.news_item_place_holder)
                            .into(titleImage)
                        titleRight.text = lNewsItem.title
                        snippetRight.text = lNewsItem.snippet
                        dateRight.text = lNewsItem.date
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