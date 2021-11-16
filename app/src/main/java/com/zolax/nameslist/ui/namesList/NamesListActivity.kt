package com.zolax.nameslist.ui.namesList

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.zolax.nameslist.databinding.ActivityNamesListBinding
import com.zolax.nameslist.utils.Resource
import com.zolax.nameslist.utils.appComponent
import com.zolax.nameslist.utils.viewBinding
import timber.log.Timber
import javax.inject.Inject

class NamesListActivity : AppCompatActivity() {

    private val binding: ActivityNamesListBinding by viewBinding(ActivityNamesListBinding::inflate)

    @Inject
    lateinit var factory: NamesListViewModelFactory

    private val namesAdapter = NamesListAdapter()

    private val viewModel: NamesListViewModel by viewModels() { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        initAdapters()
        initObservers()
        viewModel.getNames()
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
                    showLoading(false)
                    Snackbar.make(binding.root, "Error", Snackbar.LENGTH_SHORT).show()
                }
                Resource.Loading -> showLoading(true)
                is Resource.Success -> {
                    showLoading(false)
                    namesAdapter.names = result.data
                }
            }
        })
    }

    private fun showLoading(isShown: Boolean) {
        binding.progressLoading.isVisible = isShown
    }
}