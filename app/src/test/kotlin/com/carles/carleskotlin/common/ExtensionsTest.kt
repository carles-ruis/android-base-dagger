package com.carles.carleskotlin.common

import android.content.SharedPreferences
import com.carles.carleskotlin.R
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.spy
import org.amshove.kluent.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ExtensionsTest {

    private lateinit var sharedPreferences : SharedPreferences
    private lateinit var throwable : Throwable
    private val editor : SharedPreferences.Editor = mock()

    @Before
    fun setup() {
        sharedPreferences = spy()
        throwable = Throwable("some_message")
    }

    @Test
    fun sharedPreferences_shouldGetCacheExpirationTime() {
        sharedPreferences.getCacheExpirationTime("someclass", "1")
        Verify on sharedPreferences that sharedPreferences.getLong("expiration_time_someclass1", 0L)
    }

    @Test
    fun sharedPreferences_shouldSetCacheExpirationTime() {
        When calling sharedPreferences.edit() itReturns editor
        When calling editor.putLong(any(), any()) itReturns editor
        sharedPreferences.setCacheExpirationTime("someclass", "1", 99L)
        Verify on editor that editor.putLong("expiration_time_someclass1", 99L)
    }

    @Test
    fun throwable_shouldGetMessageId() {
        throwable.getMessageId() shouldEqualTo  R.string.error_server_response
    }
}