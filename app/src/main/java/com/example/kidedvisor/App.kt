package com.example.kidedvisor

import android.app.Application
import com.example.kidedvisor.core.di.dataCoreModule
import com.example.kidedvisor.sample.di.interactorSampleModule
import com.example.kidedvisor.sample.di.repositorySampleModule
import com.example.kidedvisor.search.di.interactorSearchModule
import com.example.kidedvisor.search.di.repositorySearchModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                dataCoreModule,
                repositorySampleModule,
                interactorSampleModule,
                repositorySearchModule,
                interactorSearchModule,
            )
        }
    }
}