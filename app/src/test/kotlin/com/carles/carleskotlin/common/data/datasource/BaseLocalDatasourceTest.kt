package com.carles.carleskotlin.common.data.datasource

import android.content.SharedPreferences
import com.carles.carleskotlin.common.getCacheExpirationTime
import org.amshove.kluent.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class BaseLocalDatasourceTest {

    private lateinit var datasource: BaseLocalDatasource
    private val sharedPreferences: SharedPreferences = mock()

    @Before
    fun setup() {
        datasource = object : BaseLocalDatasource(sharedPreferences) {}
    }

    @Test
    fun calculateCacheExpirationTime_shouldReturnFutureTime() {
        datasource.calculateCacheExpirationTime() shouldBeGreaterThan System.currentTimeMillis()
    }

    @Test
    fun isExpired_shouldCheckSharedPreferences() {
        datasource.isExpired("someclass", "someid")
        Verify on sharedPreferences that sharedPreferences.getCacheExpirationTime("someclass", "someid") was called
    }
}