package com.mobile

import android.app.Application
import com.mobile.newsbuzz.data.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class NewsBuzzApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initTimber()
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}