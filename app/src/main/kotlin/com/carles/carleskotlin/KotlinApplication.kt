package com.carles.carleskotlin

import android.app.Application
import io.realm.Realm

class KotlinApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
    }
}