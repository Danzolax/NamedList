package com.zolax.nameslist.ui.namesList

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.google.android.material.snackbar.Snackbar
import com.zolax.nameslist.R
import com.zolax.nameslist.databinding.ActivityMainBinding
import com.zolax.nameslist.utils.Resource
import com.zolax.nameslist.utils.appComponent
import com.zolax.nameslist.utils.viewBinding
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by viewBinding(ActivityMainBinding::inflate)

    @Inject
    lateinit var factory: NamesListViewModelFactory

    private val viewModel: NamesListViewModel by viewModels() { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        initObservers()
        viewModel.getNames()

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
                    binding.text.text = result.data.toString()
                }
            }
        })
    }

    private fun showLoading(isShown: Boolean) {
        if (isShown) {
            binding.text.text = "Loading"
        }
    }
}