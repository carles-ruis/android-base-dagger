package com.carles.carleskotlin.poi.data.datasource

import android.content.SharedPreferences
import com.carles.carleskotlin.common.data.datasource.BaseLocalDatasource
import com.carles.carleskotlin.common.setCacheExpirationTime
import com.carles.carleskotlin.poi.data.entity.PoiRealmObject
import com.carles.carleskotlin.poi.model.Poi
import com.carles.carleskotlin.poi.toModel
import com.carles.carleskotlin.poi.toRealmObject
import io.reactivex.Maybe
import io.realm.Realm
import javax.inject.Inject

class PoiLocalDatasource @Inject constructor(sharedPreferences: SharedPreferences) : BaseLocalDatasource(sharedPreferences) {

    fun getPoiList(): Maybe<List<Poi>> = Maybe.empty()

    fun getPoiDetail(id: String): Maybe<Poi> {
        return Maybe.defer {
            var poi: Poi? = null
            if (!isExpired(PoiRealmObject::class.java.name, id)) {
                val realm = Realm.getDefaultInstance()
                val poiRealmObject = realm.where(PoiRealmObject::class.java).equalTo(PoiRealmObject.ID, id).findFirst()
                poi = poiRealmObject?.toModel()
                realm.close()
            }
            if (poi == null) Maybe.empty() else Maybe.just(poi)
        }
    }

    fun persist(poi: Poi) {
        val poiRealmObject = poi.toRealmObject()
        val realm = Realm.getDefaultInstance()
        realm.executeTransaction {
            it.copyToRealmOrUpdate(poiRealmObject)
        }
        realm.close()
        sharedPreferences.setCacheExpirationTime(PoiRealmObject::class.java.name, poi.id, calculateCacheExpirationTime())
    }

}