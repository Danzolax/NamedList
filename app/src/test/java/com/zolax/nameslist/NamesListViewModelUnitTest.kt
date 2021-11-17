package com.zolax.nameslist

import android.app.Application
import android.os.Looper
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.google.android.material.snackbar.Snackbar
import com.zolax.nameslist.data.repositories.BaseRepository
import com.zolax.nameslist.data.repositories.BaseRepositoryimpl
import com.zolax.nameslist.ui.namesList.NamesListViewModel
import com.zolax.nameslist.utils.Resource
import io.reactivex.rxjava3.core.Observable
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.util.logging.Handler
import android.R
import android.os.Looper.getMainLooper
import org.robolectric.Robolectric
import org.robolectric.Shadows.shadowOf


@RunWith(RobolectricTestRunner::class)
@Config(application = Application::class, manifest = Config.NONE)
class NamesListViewModelUnitTest {


    private lateinit var viewModel: NamesListViewModel


    @Before
    fun initViewModel() {
        viewModel = NamesListViewModel(FakeRepository())

    }


    @Test
    fun `show what states of livedata correct working and not running for too long`() {
        viewModel.names.observeForever {}
        viewModel.getNames()
        assert(viewModel.names.value is Resource.Loading)
        Thread.sleep(100)
        shadowOf(getMainLooper()).idle()
        assert(viewModel.names.value is Resource.Success)
    }

    class FakeRepository : BaseRepository {
        override fun getNames(): Observable<String> {
            return Observable.fromArray("Item1", "Item2", "Item3")
        }

    }
}