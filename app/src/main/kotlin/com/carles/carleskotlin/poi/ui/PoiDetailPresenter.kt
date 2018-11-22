package com.carles.carleskotlin.poi.ui

import com.carles.carleskotlin.common.getMessageId
import com.carles.carleskotlin.common.ui.BasePresenter
import com.carles.carleskotlin.poi.repository.PoiRepository
import io.reactivex.Scheduler
import javax.inject.Inject
import javax.inject.Named

class PoiDetailPresenter @Inject constructor(
    @Named("uiScheduler") uiScheduler: Scheduler, @Named("processScheduler") processScheduler: Scheduler, val poiRepository: PoiRepository
) : BasePresenter<PoiDetailView>(uiScheduler, processScheduler), PoiDetailPresenterContract {

    private lateinit var id: String

    override fun initialize(id: String) {
        this.id = id
    }

    override fun onViewCreated(view: PoiDetailView) {
        super.onViewCreated(view)
        getPoiDetail()
    }

    private fun getPoiDetail() {
        view?.showProgress()
        addDisposable(poiRepository.getPoiDetail(id).subscribeOn(processScheduler).observeOn(uiScheduler).subscribe(
            { view?.hideProgress(); view?.displayPoiDetail(it) },
            { view?.showError(it.getMessageId(), { getPoiDetail() }) }
        ))
    }
}