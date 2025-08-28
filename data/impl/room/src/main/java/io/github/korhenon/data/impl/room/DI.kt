package io.github.korhenon.data.impl.room

import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val dataImplRoomModule = module {
    factory {
        Room.databaseBuilder(androidContext(), AppDatabase::class.java, "AppDatabase").build()
    }
    factory {
        get<AppDatabase>().AppInfoDao()
    }
    singleOf(::AppInfoCacheDataSourceImpl) bind  CacheDataSource.AppInfoCacheDataSource::class
}