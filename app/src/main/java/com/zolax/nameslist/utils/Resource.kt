package com.zolax.nameslist.utils

sealed class Resource<out T> {
    class Success<T>(
        val data: T
    ) : Resource<T>()

    class Error(val error: Throwable) : Resource<Nothing>()

    object Loading : Resource<Nothing>()
}
