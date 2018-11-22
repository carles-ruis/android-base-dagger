package com.carles.carleskotlin

import android.app.Application
import android.content.Context
import android.preference.PreferenceManager
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Named
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    internal fun provideContext(application: Application): Context = application

    @Provides
    @Singleton
    internal fun provideSharedPreferences(context: Context) = PreferenceManager.getDefaultSharedPreferences(context)

    @Provides
    @Singleton
    @Named("uiScheduler")
    internal fun provideUiScheduler(): Scheduler = AndroidSchedulers.mainThread()

    @Provides
    @Singleton
    @Named("processScheduler")
    internal fun provideProcessScheduler(): Scheduler = Schedulers.io()
}