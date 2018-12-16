package com.carles.carleskotlin.common.ui

import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BasePresenter<V : BaseView>(protected val uiScheduler: Scheduler, protected val processScheduler: Scheduler) : BaseContract<V> {

    internal val disposables = CompositeDisposable()
    internal var view: V? = null

    override fun onViewCreated(view: V) {
        this.view = view
    }

    override fun onViewDestroyed() {
        if (!disposables.isDisposed) disposables.dispose()
        this.view = null
    }

    internal fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }

}