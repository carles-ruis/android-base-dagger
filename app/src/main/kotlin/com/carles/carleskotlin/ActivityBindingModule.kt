package com.carles.carleskotlin

import com.carles.carleskotlin.poi.PoiModule
import com.carles.carleskotlin.poi.ui.PoiDetailActivity
import com.carles.carleskotlin.poi.ui.PoiListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = [PoiModule::class])
    @ActivityScope
    abstract fun poiListActivity(): PoiListActivity

    @ContributesAndroidInjector(modules = [PoiModule::class])
    @ActivityScope
    abstract fun poiDetailActivity(): PoiDetailActivity
}