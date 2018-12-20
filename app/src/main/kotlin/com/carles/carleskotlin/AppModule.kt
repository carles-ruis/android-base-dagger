package com.carles.carleskotlin

import android.content.Context
import android.preference.PreferenceManager
import com.carles.carleskotlin.common.ui.BaseView
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Named
import javax.inject.Singleton

@Module
object AppModule {

    @Provides
    internal fun provideContext(baseView: BaseView) = baseView.getContext()

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