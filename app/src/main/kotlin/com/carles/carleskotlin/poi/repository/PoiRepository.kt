package com.carles.carleskotlin.poi.repository

import com.carles.carleskotlin.poi.data.datasource.PoiCloudDatasource
import com.carles.carleskotlin.poi.data.datasource.PoiLocalDatasource
import com.carles.carleskotlin.poi.model.Poi
import io.reactivex.Maybe
import io.reactivex.Single
import javax.inject.Inject

class PoiRepository @Inject constructor(val poiLocalDatasource: PoiLocalDatasource, val poiCloudDatasource: PoiCloudDatasource) {

    fun getPoiList(): Single<List<Poi>> =
            Maybe.concat(poiLocalDatasource.getPoiList(), poiCloudDatasource.getPoiList().toMaybe()).firstOrError();

    fun getPoiDetail(id: String): Single<Poi> =
            Maybe.concat(poiLocalDatasource.getPoiDetail(id), poiCloudDatasource.getPoiDetail(id).toMaybe()).firstOrError();
}