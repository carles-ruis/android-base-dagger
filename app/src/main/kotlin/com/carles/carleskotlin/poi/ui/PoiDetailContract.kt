package com.carles.carleskotlin.poi.ui

import com.carles.carleskotlin.common.ui.BasePresenterContract
import com.carles.carleskotlin.common.ui.BaseView
import com.carles.carleskotlin.poi.model.Poi

interface PoiDetailView : BaseView {
    fun displayPoiDetail(poi: Poi)
}

interface PoiDetailPresenterContract : BasePresenterContract<PoiDetailView> {
    fun initialize(id: String)
}