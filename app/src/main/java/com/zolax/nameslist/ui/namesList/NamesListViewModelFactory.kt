package com.zolax.nameslist.ui.namesList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zolax.nameslist.data.repositories.BaseRepository
import javax.inject.Inject

class NamesListViewModelFactory @Inject constructor(
    private val repository: BaseRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        require(modelClass == NamesListViewModel::class)
        return NamesListViewModel(repository) as T
    }
}