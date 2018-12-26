package com.carles.carleskotlin.poi.data.datasource

import com.carles.carleskotlin.poi.data.entity.PoiListResponseDto
import com.carles.carleskotlin.poi.data.entity.PoiResponseDto
import com.carles.carleskotlin.poi.model.Poi
import com.carles.carleskotlin.poi.toModel
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.mock
import io.reactivex.Single
import org.amshove.kluent.Verify
import org.amshove.kluent.on
import org.amshove.kluent.that
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PoiCloudDatasourceTest {

    private lateinit var datasource: PoiCloudDatasource
    private val localDatasource: PoiLocalDatasource = mock()
    private val poiDetail = Poi("the_poi")
    private val poiList = listOf(poiDetail)
    private val poiDetailDto: PoiResponseDto = mock {
        on { toModel() } doReturn poiDetail
    }
    private val poiListDto: PoiListResponseDto = mock {
        on { toModel() } doReturn poiList
    }
    private val service: PoiService = mock {
        on { getPoiList() } doReturn Single.just(poiListDto)
        on { getPoiDetail(eq("some_id")) } doReturn Single.just(poiDetailDto)
    }

    @Before
    fun setup() {
        datasource = PoiCloudDatasource(localDatasource, service)
    }

    @Test
    fun getPoiList_shouldPerformRequest() {
        datasource.getPoiList().test().assertValue(poiList).assertComplete()
        Verify on service that service.getPoiList()
        Verify on poiListDto that poiListDto.toModel()
    }

 /*   @Test
    fun getPoiDetail_shouldPerformRequest() {
        datasource.getPoiDetail("some_id").test().assertValue(poiDetail)
        Verify on service that service.getPoiDetail("some_id") was called
        Verify on poiDetailDto that poiDetailDto.toModel() was called
    }*/
}