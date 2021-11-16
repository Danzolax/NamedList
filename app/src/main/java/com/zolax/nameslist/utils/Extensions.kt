package com.zolax.nameslist.utils

import android.content.Context
import com.zolax.nameslist.App
import com.zolax.nameslist.di.AppComponent
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.functions.BiFunction
import java.util.concurrent.TimeUnit


//Получение графа из любого активити/фрагмента/сервиса/ресивера
val Context.appComponent: AppComponent
    get() = when(this){
        is App -> appComponent
        else -> this.applicationContext.appComponent
    }



//Задержка перед каждым OnNext
fun <T> Observable<T>.delayEach(interval: Long, timeUnit: TimeUnit): Observable<T> =
    Observable.zip(
        this,
        Observable.interval(interval, timeUnit),
        { item, _ -> item }
    )
