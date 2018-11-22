package com.carles.carleskotlin

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.carles.carleskotlin.poi.data.datasource.PoiService
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import io.reactivex.Scheduler
import javax.inject.Named
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, AppModule::class, ServiceModule::class, ActivityBindingModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun service(serviceModule: ServiceModule): Builder

        fun build(): AppComponent
    }

    fun inject(application: KotlinApplication)

    fun context(): Context

    fun sharedPreferences() : SharedPreferences

    @Named("uiScheduler")
    fun uiScheduler(): Scheduler

    @Named("processScheduler")
    fun processScheduler(): Scheduler

    fun poiService(): PoiService
}