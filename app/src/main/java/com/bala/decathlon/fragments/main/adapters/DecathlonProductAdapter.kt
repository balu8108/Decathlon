package com.bala.decathlon.fragments.main.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bala.decathlon.databinding.DecathlonProductLayoutBinding
import com.bala.decathlon.fragments.main.data.DecathlonProduct

class DecathlonProductAdapter(
    private val context: Context
) : PagingDataAdapter<DecathlonProduct, RecyclerView.ViewHolder>(DiffCallBackDecathlonProducts()) {

    inner class DecathlonProductViewHolder(val viewBinding: DecathlonProductLayoutBinding) :
        RecyclerView.ViewHolder(viewBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return DecathlonProductViewHolder(
            DecathlonProductLayoutBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is DecathlonProductViewHolder) {
            holder.viewBinding.apply {
                val lDecathlonProduct = getItem(position)
                lDecathlonProduct?.let {
                    holder.viewBinding.apply {
                        /*Glide.with(context)
                            .load(lNewsItem.imageUrl)
                            .placeholder(R.drawable.news_item_place_holder)
                            .into(titleImage)*/
                        name.text = lDecathlonProduct.name
                        price.text = "₹ "+lDecathlonProduct.price
                        brand.text = lDecathlonProduct.brand
                    }
                }

            }
        }
    }


    class DiffCallBackDecathlonProducts : DiffUtil.ItemCallback<DecathlonProduct>() {
        override fun areItemsTheSame(oldItem: DecathlonProduct, newItem: DecathlonProduct) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: DecathlonProduct, newItem: DecathlonProduct) =
            oldItem == newItem
    }
}