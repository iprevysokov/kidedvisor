package com.example.kidedvisor.core.di

import androidx.room.Room
import com.example.kidedvisor.core.roomdb.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {

    single {
        Room.databaseBuilder(
            androidContext(), AppDatabase::class.java, "KidedvisorDatabase.db"
        )
            .build()
    }
}