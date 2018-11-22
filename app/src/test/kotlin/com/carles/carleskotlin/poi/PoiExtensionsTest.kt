package com.carles.carleskotlin.poi

import com.carles.carleskotlin.createEmptyPoiListResponseDto
import com.carles.carleskotlin.createPoi
import com.carles.carleskotlin.createPoiListResponseDto
import com.carles.carleskotlin.createPoiResponseDto
import com.carles.carleskotlin.poi.data.entity.PoiRealmObject
import org.amshove.kluent.should
import org.amshove.kluent.shouldBeNull
import org.amshove.kluent.shouldEqual
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PoiExtensionsTest {

    @Test
    fun poiListResponseDto_toModel() {
        createEmptyPoiListResponseDto().toModel() shouldEqual emptyList()

        val dto = createPoiListResponseDto()
        dto.toModel().should { size == dto.list!!.size && get(0).id == dto.list!!.get(0).id }
    }

    @Test
    fun poiResponseDto_toModel() {
        val dto = createPoiResponseDto()
        dto.toModel().should {
            id == dto.id && title == dto.title && transport == dto.transport && email == dto.email && phone == dto.phone
        }

        dto.transport = ""; dto.toModel().transport.shouldBeNull()
        dto.email = "null"; dto.toModel().email.shouldBeNull()
        dto.phone = "undefined"; dto.toModel().phone.shouldBeNull()
    }

    @Test
    fun poi_toRealmObject() {
        val poi = createPoi(System.currentTimeMillis().toString())
        poi.toRealmObject().id shouldEqual poi.id
    }

    @Test
    fun poiRealmObject_toModel() {
        val poiRealmObject = PoiRealmObject(id = System.currentTimeMillis().toString())
        poiRealmObject.toModel().id shouldEqual poiRealmObject.id
    }
}