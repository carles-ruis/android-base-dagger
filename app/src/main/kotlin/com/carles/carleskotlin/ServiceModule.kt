package com.carles.carleskotlin

import com.carles.carleskotlin.poi.data.datasource.PoiService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class ServiceModule {

    @Provides
    @Singleton
    @Named("baseUrl")
    internal fun provideBaseUrl() = "https://t21services.herokuapp.com"

    @Provides
    @Singleton
    internal fun provideRetrofit(@Named("baseUrl") baseUrl: String) =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    @Provides
    @Singleton
    internal fun providePoiService(retrofit: Retrofit) = retrofit.create(PoiService::class.java)
}