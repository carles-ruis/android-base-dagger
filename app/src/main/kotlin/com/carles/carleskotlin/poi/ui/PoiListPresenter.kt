package com.carles.carleskotlin.poi.ui

import com.carles.carleskotlin.common.getMessageId
import com.carles.carleskotlin.common.ui.BasePresenter
import com.carles.carleskotlin.poi.model.Poi
import com.carles.carleskotlin.poi.repository.PoiRepository
import io.reactivex.Scheduler
import javax.inject.Inject
import javax.inject.Named

class PoiListPresenter @Inject constructor(
    @Named("uiScheduler") uiScheduler: Scheduler, @Named("processScheduler") processScheduler: Scheduler, val poiRepository: PoiRepository
) : BasePresenter<PoiListView>(uiScheduler, processScheduler), PoiListContract {

    override fun onViewCreated(view: PoiListView) {
        super.onViewCreated(view)
        getPoiList()
    }

    private fun getPoiList() {
        view?.showProgress()
        addDisposable(poiRepository.getPoiList().subscribeOn(processScheduler).observeOn(uiScheduler).subscribe(
            { view?.hideProgress(); view?.displayPoiList(it) },
            { view?.showError(messageId = it.getMessageId(), onRetry = { getPoiList() }) }
        ))
    }

    override fun onPoiClicked(poi: Poi) {
        view?.navigateToPoiDetail(poi.id)
    }
}