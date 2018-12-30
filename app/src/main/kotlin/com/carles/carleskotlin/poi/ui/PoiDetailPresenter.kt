package com.carles.carleskotlin.poi.ui

import com.carles.carleskotlin.AppModule
import com.carles.carleskotlin.ServiceModule
import com.carles.carleskotlin.common.getMessageId
import com.carles.carleskotlin.common.ui.BasePresenter
import com.carles.carleskotlin.poi.DaggerPoiPresenterInjector
import com.carles.carleskotlin.poi.PoiModule
import com.carles.carleskotlin.poi.repository.PoiRepository
import io.reactivex.Scheduler
import javax.inject.Inject

class PoiDetailPresenter(poiDetailView: PoiDetailView, private val id: String, val uiScheduler: Scheduler, val processScheduler: Scheduler, test: Boolean = false) : BasePresenter<PoiDetailView>(poiDetailView) {

    @Inject
    lateinit var poiRepository: PoiRepository

    init {
        if (!test)
            DaggerPoiPresenterInjector.builder()
                .baseView(view)
                .appModule(AppModule)
                .serviceModule(ServiceModule)
                .poiModule(PoiModule)
                .build()
                .inject(this)
    }

    override fun onViewCreated() {
        super.onViewCreated()
        getPoiDetail()
    }

    private fun getPoiDetail() {
        view.showProgress()
        addDisposable(poiRepository.getPoiDetail(id).subscribeOn(processScheduler).observeOn(uiScheduler).subscribe(
            { view.hideProgress(); view.displayPoiDetail(it) },
            { view.showError(it.getMessageId(), { getPoiDetail() }) }
        ))
    }
}