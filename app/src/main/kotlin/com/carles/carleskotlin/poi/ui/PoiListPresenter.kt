package com.carles.carleskotlin.poi.ui

import com.carles.carleskotlin.AppModule
import com.carles.carleskotlin.ServiceModule
import com.carles.carleskotlin.common.getMessageId
import com.carles.carleskotlin.common.ui.BasePresenter
import com.carles.carleskotlin.poi.DaggerPoiPresenterInjector
import com.carles.carleskotlin.poi.PoiModule
import com.carles.carleskotlin.poi.model.Poi
import com.carles.carleskotlin.poi.repository.PoiRepository
import javax.inject.Inject

class PoiListPresenter(poiListView: PoiListView) : BasePresenter<PoiListView>(poiListView) {

    @Inject
    lateinit var poiRepository:PoiRepository

    init {
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
        getPoiList()
    }

    private fun getPoiList() {
        view.showProgress()
        addDisposable(poiRepository.getPoiList().subscribeOn(processScheduler).observeOn(uiScheduler).subscribe(
            { view.hideProgress(); view.displayPoiList(it) },
            { view.showError(messageId = it.getMessageId(), onRetry = { getPoiList() }) }
        ))
    }

    fun onPoiClicked(poi: Poi) {
        view.navigateToPoiDetail(poi.id)
    }
}