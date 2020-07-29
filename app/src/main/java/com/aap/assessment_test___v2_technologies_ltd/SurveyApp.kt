package com.aap.assessment_test___v2_technologies_ltd

import android.app.Application
import com.aap.assessment_test___v2_technologies_ltd.data.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class SurveyApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@SurveyApp)
            modules(listOf(
                dbModule,
                netModule,
                mapperModule,
                repoModule,
                useCaseModule,
                viewModelModule
            ))
        }
    }
}