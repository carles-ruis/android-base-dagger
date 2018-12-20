package com.carles.carleskotlin.poi

import com.carles.carleskotlin.poi.data.datasource.PoiCloudDatasource
import com.carles.carleskotlin.poi.data.datasource.PoiLocalDatasource
import com.carles.carleskotlin.poi.repository.PoiRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object PoiModule {
    @Provides
    @Singleton
    internal fun providePoiRepository(poiLocalDatasource: PoiLocalDatasource, poiCloudDatasource: PoiCloudDatasource) =
        PoiRepository(poiLocalDatasource, poiCloudDatasource)

}