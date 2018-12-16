package com.carles.carleskotlin.common.ui

interface BaseView {
    fun showProgress()
    fun hideProgress()
    fun showError(messageId: Int, onRetry: (() -> Unit)? = null)
}

interface BaseContract<V : BaseView> {
    fun onViewCreated(view: V)
    fun onViewDestroyed()
}