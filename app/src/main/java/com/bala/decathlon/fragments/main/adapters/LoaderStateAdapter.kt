package com.bala.decathlon.fragments.main.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bala.decathlon.databinding.DecathlonProductLoaderBinding

class LoaderStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<LoaderStateAdapter.LoaderViewHolder>() {

    override fun onBindViewHolder(holder: LoaderViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoaderViewHolder {
        return LoaderViewHolder.getInstance(parent, retry)
    }

    /**
     * view holder class for footer loader and error state handling
     */
    class LoaderViewHolder(private val viewBinding: DecathlonProductLoaderBinding, retry: () -> Unit) :
        RecyclerView.ViewHolder(viewBinding.root) {

        companion object {
            fun getInstance(parent: ViewGroup, retry: () -> Unit): LoaderViewHolder {
                return LoaderViewHolder(
                    DecathlonProductLoaderBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    ), retry
                )
            }
        }


        init {
            viewBinding.btnRetry.setOnClickListener {
                retry()
            }
        }

        fun bind(loadState: LoadState) {
            if (loadState is LoadState.Loading) {
                viewBinding.root.transitionToEnd()
            } else {
                viewBinding.root.transitionToStart()
            }
        }
    }
}