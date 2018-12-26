package com.carles.carleskotlin.common.ui

import org.amshove.kluent.mock
import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldBeFalse
import org.amshove.kluent.shouldBeTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class BasePresenterTest {

    private lateinit var presenter : BasePresenter<BaseView>

    @Before
    fun setup() {
        presenter = object : BasePresenter<BaseView>(mock()) {}
        presenter.uiScheduler = mock()
        presenter.processScheduler = mock()
    }

    @Test
    fun onViewDestroyed_shouldDispose() {
        presenter.addDisposable(mock())
        presenter.onViewDestroyed()
        presenter.disposables.isDisposed.shouldBeTrue()
    }

    @Test
    fun addDisposable_shouldAdd() {
        presenter.addDisposable(mock())
        presenter.disposables.size() shouldBe 1
        presenter.disposables.isDisposed.shouldBeFalse()
    }
}