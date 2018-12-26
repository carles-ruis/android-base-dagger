package com.carles.carleskotlin.poi.data.datasource

import android.content.SharedPreferences
import com.carles.carleskotlin.common.setCacheExpirationTime
import com.carles.carleskotlin.poi.data.entity.PoiRealmObject
import com.carles.carleskotlin.poi.model.Poi
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.spy
import com.nhaarman.mockito_kotlin.whenever
import io.realm.Realm
import io.realm.RealmQuery
import org.amshove.kluent.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyLong
import org.mockito.ArgumentMatchers.anyString
import org.powermock.api.mockito.PowerMockito
import org.powermock.api.mockito.PowerMockito.mockStatic
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner

@RunWith(PowerMockRunner::class)
@PrepareForTest(Realm::class, SharedPreferences::class)
class PoiLocalDatasourceTest {

    private lateinit var datasource: PoiLocalDatasource
    private val sharedPreferences: SharedPreferences = mock()
    private val realm: Realm = mock()
    private val realmQuery: RealmQuery<PoiRealmObject> = mock()

    @Before
    fun setup() {
        mockStatic(Realm::class.java)
        whenever(Realm.getDefaultInstance()) doReturn realm
        whenever(realm.where<PoiRealmObject>(any())) doReturn realmQuery
        whenever(ExtensionsKt.)
        mockStatic(SharedPreferences::class.java)
        PowerMockito.doNothing().`when`(SharedPreferences::class.java, "setCacheExpirationTime", any(), any(), any())
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
        doReturn(false).whenever(spy).isExpired(anyString(), anyString())
        whenever(realmQuery.equalTo(anyString(), anyString())) doReturn realmQuery
        whenever(realmQuery.findFirst()) doReturn null as? PoiRealmObject

        spy.getPoiDetail("some_id").test().assertNoValues().assertComplete()
        Verify on realm that realm.where(eq(PoiRealmObject::class.java))
        Verify on realm that realm.close()
    }

    @Test
    fun getPoiDetail_shouldReturnStoredValues() {
        val spy = spy(datasource)
        doReturn(false).whenever(spy).isExpired(anyString(), anyString())
        whenever(realmQuery.equalTo(anyString(), anyString())) doReturn realmQuery
        whenever(realmQuery.findFirst()) doReturn PoiRealmObject("some_id")

        spy.getPoiDetail("some_id").test().assertValue(Poi("some_id")).assertComplete()
        Verify on realm that realm.where(PoiRealmObject::class.java)
        Verify on realm that realm.close()
    }

    @Test
    fun persist_shouldPersistToRealm() {
        val poi = Poi("some_id")


        datasource.persist(poi)

        Verify on realm that realm.copyToRealmOrUpdate(any(PoiRealmObject::class))
        Verify on realm that realm.close()
        Verify on sharedPreferences that sharedPreferences.setCacheExpirationTime(any(), eq("some_id"), anyLong())
    }


}