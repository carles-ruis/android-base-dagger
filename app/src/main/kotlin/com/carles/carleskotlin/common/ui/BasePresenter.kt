package com.carles.carleskotlin.common.ui

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BasePresenter<out V : BaseView>(protected val view: V) {

/*    @Inject
    @field:Named("uiScheduler")
    lateinit var uiScheduler: Scheduler
    @Inject
    @field:Named("processScheduler")
    lateinit var processScheduler: Scheduler*/

    internal val disposables = CompositeDisposable()

    open fun onViewCreated() {}

    open fun onViewDestroyed() {
        if (!disposables.isDisposed) disposables.dispose()
    }

    internal fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }

}