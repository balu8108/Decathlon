package com.bala.nytnews.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bala.nytnews.databinding.MainFragmentBinding

class MainFragment : Fragment() {

    /*companion object {
        fun newInstance() = MainFragment()
    }*/

    private val viewModel: MainViewModel by viewModels()

    private val newsListAdapter by lazy { NewsListAdapter(requireContext()) }

    private val viewBinding
        get() = _viewBinding!!
    private var _viewBinding: MainFragmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewBinding = MainFragmentBinding.inflate(inflater)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.makeNetworkRequest()
        init()
        initObservers()
    }

    private fun init() {
        viewBinding.newsList.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        viewBinding.newsList.adapter = newsListAdapter
    }

    private fun initObservers() {
        viewModel.newsItems.observe(viewLifecycleOwner) { lNewsItems ->
            newsListAdapter.submitList(lNewsItems)
            lNewsItems?.forEach { lNewsItem ->
                Log.d("balatag", lNewsItem.toString())
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _viewBinding = null
    }

}