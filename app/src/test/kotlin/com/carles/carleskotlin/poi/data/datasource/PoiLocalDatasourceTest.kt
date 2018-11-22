package com.carles.carleskotlin.poi.data.datasource

import android.content.SharedPreferences
import com.carles.carleskotlin.poi.data.entity.PoiRealmObject
import com.carles.carleskotlin.poi.model.Poi
import com.carles.carleskotlin.poi.toModel
import com.nhaarman.mockito_kotlin.*
import io.realm.Realm
import io.realm.RealmQuery
import org.amshove.kluent.Verify
import org.amshove.kluent.VerifyNoInteractions
import org.amshove.kluent.on
import org.amshove.kluent.that
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyString
import org.powermock.api.mockito.PowerMockito
import org.powermock.api.mockito.PowerMockito.mockStatic
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner

@RunWith(PowerMockRunner::class)
@PrepareForTest(Realm::class, PoiRealmObject::class)
class PoiLocalDatasourceTest {

    private lateinit var datasource: PoiLocalDatasource
    private val sharedPreferences: SharedPreferences = mock()
    private val realm: Realm = mock()
    private val realmQuery: RealmQuery<PoiRealmObject> = mock()
    private val poi: Poi = Poi("some_id")
    private val poiRealmObject: PoiRealmObject = mock()

    @Before
    fun setup() {
        mockStatic(Realm::class.java)
        whenever(Realm.getDefaultInstance()) doReturn realm
        whenever(realm.where<PoiRealmObject>(any())) doReturn realmQuery
        mockStatic(PoiRealmObject::class.java)
        PowerMockito.`when`(PoiRealmObject.toModel()) doReturn Poi("some_id")
        datasource = PoiLocalDatasource(sharedPreferences)
    }

    @Test
    fun getPoiList_shouldReturnEmpty() {
        datasource.getPoiList().test().assertNoValues().assertComplete()
    }

    @Test
    fun getPoiDetail_shouldReturnNoValuesIfCacheExpired() {
        val spy = spy(datasource)
        doReturn(true).whenever(spy).isExpired(anyString(), anyString())
        spy.getPoiDetail("some_id").test().assertNoValues().assertComplete()
        VerifyNoInteractions on realm
    }

    @Test
    fun getPoiDetail_shouldReturnNoValuesIfNoData() {
        val spy = spy(datasource)
        whenever(realmQuery.equalTo(anyString(), anyString())) doReturn realmQuery
        whenever(realmQuery.findFirst()) doReturn null as? PoiRealmObject
        doReturn(false).whenever(spy).isExpired(anyString(), anyString())

        spy.getPoiDetail("some_id").test().assertNoValues().assertComplete()
        Verify on realm that realm.where(eq(PoiRealmObject::class.java))
        Verify on realm that realm.close()
    }

    @Test
    fun getPoiDetail_shouldReturnStoredValues() {
        whenever(realmQuery.equalTo(anyString(), anyString())) doReturn realmQuery
        whenever(realmQuery.findFirst()) doReturn poiRealmObject
        val spy = spy(datasource)
        doReturn(false).whenever(spy).isExpired(anyString(), anyString())

        spy.getPoiDetail("some_id").test().assertValue(poi).assertComplete()
        Verify on realm that realm.where(PoiRealmObject::class.java)
        Verify on realm that realm.close()
    }



}