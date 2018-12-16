package com.carles.carleskotlin.poi

import com.carles.carleskotlin.ActivityScope
import com.carles.carleskotlin.poi.ui.PoiDetailPresenter
import com.carles.carleskotlin.poi.ui.PoiDetailContract
import com.carles.carleskotlin.poi.ui.PoiListPresenter
import com.carles.carleskotlin.poi.ui.PoiListContract
import dagger.Module
import dagger.Provides

@Module
class PoiModule {

    @Provides
    @ActivityScope
    internal fun providePoiListPresenter(poiListPresenter: PoiListPresenter): PoiListContract =
        poiListPresenter

    @Provides
    @ActivityScope
    internal fun providePoiDetailPresenter(poiDetailPresenter: PoiDetailPresenter): PoiDetailContract =
        poiDetailPresenter
}