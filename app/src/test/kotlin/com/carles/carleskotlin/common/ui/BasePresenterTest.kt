package com.carles.carleskotlin.common.ui

import io.reactivex.Scheduler
import org.amshove.kluent.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class BasePresenterTest {

    private lateinit var presenter : BasePresenter<BaseView>
    private val uiScheduler : Scheduler = mock()
    private val processScheduler : Scheduler = mock()
    private val view : BaseView = mock()

    @Before
    fun setup() {
        presenter = object : BasePresenter<BaseView>(uiScheduler, processScheduler) {}
    }

    @Test
    fun onViewCreated_shouldAssignView() {
        presenter.onViewCreated(view)
        presenter.view shouldBe view
    }

    @Test
    fun onViewDestroyed_shouldUnsetView() {
        presenter.view = view
        presenter.onViewDestroyed()
        presenter.view.shouldBeNull()
        presenter.disposables.isDisposed.shouldBeTrue()
    }

    @Test
    fun addDisposable_shouldAdd() {
        presenter.addDisposable(mock())
        presenter.disposables.size() shouldBe 1
        presenter.disposables.isDisposed.shouldBeFalse()
    }
}