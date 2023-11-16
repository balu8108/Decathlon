package com.bala.decathlon.fragments.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.ExperimentalPagingApi
import androidx.recyclerview.widget.GridLayoutManager
import com.bala.decathlon.R
import com.bala.decathlon.databinding.MainFragmentBinding
import com.bala.decathlon.fragments.main.adapters.LoaderStateAdapter
import com.bala.decathlon.fragments.main.adapters.DecathlonProductAdapter
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class MainFragment : Fragment() {

    private val viewModel: MainViewModel by viewModels()

    private lateinit var decathlonProductAdapter: DecathlonProductAdapter

    private val viewBinding
        get() = _viewBinding!!
    private var _viewBinding: MainFragmentBinding? = null
    var job: Job? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewBinding = MainFragmentBinding.inflate(inflater)
        return viewBinding.root
    }

    @ExperimentalPagingApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        initObservers()
    }

    private fun init() {
        decathlonProductAdapter = DecathlonProductAdapter(requireContext())
        viewBinding.decathlonProductList.layoutManager = GridLayoutManager(context, 2)
        viewBinding.decathlonProductList.adapter = decathlonProductAdapter.withLoadStateFooter(LoaderStateAdapter {})
        val searchView = viewBinding.searchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                if (query.isEmpty()){
                    searchView.isIconified = true
                }
                viewModel.setQueryFilter(query)
                return false
            }

            override fun onQueryTextChange(s: String?): Boolean {
                if (s != null) {
                    viewModel.setQueryFilter(s)
                }
                return false
            }
        })
        viewBinding.toolBar.setOnMenuItemClickListener { lMenuItem ->
            if (lMenuItem.itemId == R.id.sort_by_name){
                if (lMenuItem.isChecked) {
                    lMenuItem.isChecked = false
                } else {
                    viewBinding.toolBar.menu.findItem(R.id.sort_by_price).isChecked = false
                    lMenuItem.isChecked = true
                }
                viewModel.setSortByName(lMenuItem.isChecked)
            } else if (lMenuItem.itemId == R.id.sort_by_price){
                if (lMenuItem.isChecked) {
                    lMenuItem.isChecked = false
                } else {
                    viewBinding.toolBar.menu.findItem(R.id.sort_by_name).isChecked = false
                    lMenuItem.isChecked = true
                }
                viewModel.setSortByPrice(lMenuItem.isChecked)
            }
            true
        }
    }

    @ExperimentalPagingApi
    private fun initObservers() {

        viewModel.sortByName.observe(viewLifecycleOwner) { sortByName ->
            job = viewLifecycleOwner.lifecycleScope.launch {
                viewModel.queryFilter.collectLatest {queryFilter ->
                    viewModel.sortByPrice.value?.let {
                        viewModel.getDecathlonProducts(sortByName, it,queryFilter).collectLatest { lDecathlonProduct ->
                            decathlonProductAdapter.submitData(lDecathlonProduct)
                        }
                    }
                }
            }

            viewModel.sortByPrice.observe(viewLifecycleOwner) { sortByPrice ->
                job?.cancel()
                viewLifecycleOwner.lifecycleScope.launch {
                    viewModel.queryFilter.collectLatest {queryFilter ->
                        viewModel.getDecathlonProducts(sortByName,sortByPrice,queryFilter).collectLatest { lDecathlonProduct ->
                            decathlonProductAdapter.submitData(lDecathlonProduct)
                        }
                    }
                }
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _viewBinding = null
    }

}