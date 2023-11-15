package com.bala.nytnews.fragments.main.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.LiveData
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bala.nytnews.R
import com.bala.nytnews.databinding.NewsItemLayoutBinding
import com.bala.nytnews.fragments.main.data.DecathlonProduct
import com.bumptech.glide.Glide

class NewsListAdapter(
    private val context: Context
) : PagingDataAdapter<DecathlonProduct, RecyclerView.ViewHolder>(DiffCallBackNewsItems()) {

    inner class NewsItemViewHolder(val viewBinding: NewsItemLayoutBinding) :
        RecyclerView.ViewHolder(viewBinding.root)

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
        if (holder is NewsItemViewHolder) {
            holder.viewBinding.apply {
                val lNewsItem = getItem(position)
                lNewsItem?.let {
                    holder.viewBinding.apply {
                        /*Glide.with(context)
                            .load(lNewsItem.imageUrl)
                            .placeholder(R.drawable.news_item_place_holder)
                            .into(titleImage)*/
                        name.text = lNewsItem.name
                        price.text = "₹ "+lNewsItem.price
                        brand.text = lNewsItem.brand
                    }
                }

            }
        }
    }


    class DiffCallBackNewsItems : DiffUtil.ItemCallback<DecathlonProduct>() {
        override fun areItemsTheSame(oldItem: DecathlonProduct, newItem: DecathlonProduct) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: DecathlonProduct, newItem: DecathlonProduct) =
            oldItem == newItem
    }
}