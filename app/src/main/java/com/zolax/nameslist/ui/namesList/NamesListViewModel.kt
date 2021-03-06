package com.zolax.nameslist.ui.namesList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zolax.nameslist.data.repositories.BaseRepository
import com.zolax.nameslist.utils.Resource
import com.zolax.nameslist.utils.delayEach
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.random.Random

class NamesListViewModel(private val repository: BaseRepository) : ViewModel() {
    private val _names = MutableLiveData<Resource<List<String>>>()
    val names: LiveData<Resource<List<String>>> = _names

    private val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        compositeDisposable.clear()
    }


    internal fun getNames() {
        val names = mutableListOf<String>()
        compositeDisposable.add(
            repository.getNames()
                .delayEach(Random.nextLong(1, 20), TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe { _names.value = Resource.Loading }
                .doOnError { e ->
                    _names.value = Resource.Error(e)
                    e.printStackTrace()
                }
                .doOnComplete { _names.value = Resource.Success(names) }
                .subscribe { name ->
                    names.add(name)
                }

        )


    }

    internal fun getFilteredNames(pattern: String) {
        val names = mutableListOf<String>()
        compositeDisposable.clear()
        compositeDisposable.add(
            repository.getNames()
                .delay(2, TimeUnit.SECONDS)
                .delayEach(Random.nextLong(1, 20), TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .filter {
                    it.lowercase(Locale.getDefault())
                        .startsWith(pattern.lowercase(Locale.getDefault()))
                }
                .doOnSubscribe { _names.value = Resource.Loading }
                .doOnError { e -> _names.value = Resource.Error(e) }
                .doOnComplete { _names.value = Resource.Success(names) }
                .subscribe { name ->
                    names.add(name)
                }

        )
    }


}