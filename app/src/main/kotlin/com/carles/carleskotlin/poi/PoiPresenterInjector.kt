package com.carles.carleskotlin.poi

import com.carles.carleskotlin.AppModule
import com.carles.carleskotlin.ServiceModule
import com.carles.carleskotlin.common.ui.BaseView
import com.carles.carleskotlin.poi.ui.PoiDetailPresenter
import com.carles.carleskotlin.poi.ui.PoiListPresenter
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(AppModule::class), (ServiceModule::class), (PoiModule::class)])
interface PoiPresenterInjector {

    fun inject(poiListPresenter: PoiListPresenter)
    fun inject(poiDetailPresenter: PoiDetailPresenter)

    @Component.Builder
    interface Builder {
        fun build(): PoiPresenterInjector

        fun appModule(appModule: AppModule): Builder
        fun serviceModule(serviceModule: ServiceModule): Builder
        fun poiModule(poiModule: PoiModule): Builder

        @BindsInstance
        fun baseView(baseView: BaseView): Builder
    }
}