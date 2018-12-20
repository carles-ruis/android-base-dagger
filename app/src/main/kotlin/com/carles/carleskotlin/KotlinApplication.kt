package com.carles.carleskotlin

import android.app.Application
import io.realm.Realm
import org.koin.android.ext.android.startKoin

class KotlinApplication : Application()/*, HasActivityInjector*/ {

 /*   @Inject
    internal lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> = dispatchingAndroidInjector*/

    override fun onCreate() {
        super.onCreate()
        startKoin(this, )
        Realm.init(this)
    }
}