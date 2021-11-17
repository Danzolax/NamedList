package com.zolax.nameslist.ui.namesList

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.SearchView
import android.widget.SearchView.OnQueryTextListener
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.zolax.nameslist.databinding.ActivityNamesListBinding
import com.zolax.nameslist.utils.Resource
import com.zolax.nameslist.utils.appComponent
import com.zolax.nameslist.utils.viewBinding
import javax.inject.Inject
import android.widget.Toast


class NamesListActivity : AppCompatActivity() {

    private val binding: ActivityNamesListBinding by viewBinding(ActivityNamesListBinding::inflate)

    @Inject
    lateinit var factory: NamesListViewModelFactory


    @Inject
    lateinit var namesAdapter: NamesListAdapter

    private val viewModel: NamesListViewModel by viewModels() { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        initAdapters()
        initSearch()
        initObservers()
        viewModel.getNames()
    }

    private fun initSearch() {
        binding.searchView.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    viewModel.getFilteredNames(it)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    viewModel.getFilteredNames(it)
                }
                return false
            }

        })

    }

    private fun initAdapters() {
        binding.namesListRv.apply {
            adapter = namesAdapter
            layoutManager = LinearLayoutManager(this@NamesListActivity)
        }
    }

    private fun initObservers() {
        viewModel.names.observe(this, { result ->
            when (result) {
                is Resource.Error -> {
                    result.error.printStackTrace()
                    showLoading(false)
                    Snackbar.make(binding.root, "Error", Snackbar.LENGTH_SHORT).show()
                }
                Resource.Loading -> showLoading(true)
                is Resource.Success -> {
                    val names = result.data
                    showLoading(false)
                    if (names.isEmpty()) Snackbar.make(binding.root, "Empty", Snackbar.LENGTH_SHORT)
                        .show()
                    else namesAdapter.names = names

                }
            }
        })
    }

    private fun showLoading(isShown: Boolean) {
        binding.progressLoading.isVisible = isShown
    }
}