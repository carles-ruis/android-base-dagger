package com.carles.carleskotlin.common

import android.os.Handler
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

object RxBus {

    private val bus: PublishSubject<Any> = PublishSubject.create()
    private val handler = Handler()

    fun send(any: Any) {
        bus.onNext(any)
    }

    fun sendDelayed(any: Any, delay: Long = 100) {
        handler.postDelayed({ send(any) }, delay)
    }

    fun toObservable() : Observable<Any> = bus

}