package com.bala.nytnews.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bala.nytnews.R
import com.bala.nytnews.databinding.MainFragmentBinding

class MainFragment : Fragment() {

    private val viewModel: MainViewModel by viewModels()

    private val newsListAdapter by lazy {
        NewsListAdapter(requireContext()) { url ->
            findNavController().navigate(R.id.webViewFragment, bundleOf("url" to url))
        }
    }

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
        viewBinding.newsList.adapter = newsListAdapter
    }

    private fun initObservers() {
        viewModel.newsItems.observe(viewLifecycleOwner) { lNewsItems ->
            if (lNewsItems.size > 0) {
                newsListAdapter.submitList(lNewsItems)
            }
            /*val code = 8217
            lNewsItems?.forEach { lNewsItem ->
                Log.d("balatag", '\''.toString())
                Log.d("balatag", lNewsItem.title[5].toString())
            }*/
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _viewBinding = null
    }

}