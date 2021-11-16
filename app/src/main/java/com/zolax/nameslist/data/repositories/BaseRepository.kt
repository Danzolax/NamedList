package com.zolax.nameslist.data.repositories

import io.reactivex.rxjava3.core.Observable


/*
* Интерфейс репозитория
* */
interface BaseRepository {
    //функция получения списка имен
    fun getNames(): Observable<String>
}