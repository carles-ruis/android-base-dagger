package com.carles.carleskotlin.poi

import com.carles.carleskotlin.ActivityScope
import com.carles.carleskotlin.poi.ui.PoiDetailPresenter
import com.carles.carleskotlin.poi.ui.PoiDetailPresenterContract
import com.carles.carleskotlin.poi.ui.PoiListPresenter
import com.carles.carleskotlin.poi.ui.PoiListPresenterContract
import dagger.Module
import dagger.Provides

@Module
class PoiModule {

    @Provides
    @ActivityScope
    internal fun providePoiListPresenter(poiListPresenter: PoiListPresenter): PoiListPresenterContract =
        poiListPresenter

    @Provides
    @ActivityScope
    internal fun providePoiDetailPresenter(poiDetailPresenter: PoiDetailPresenter): PoiDetailPresenterContract =
        poiDetailPresenter
}