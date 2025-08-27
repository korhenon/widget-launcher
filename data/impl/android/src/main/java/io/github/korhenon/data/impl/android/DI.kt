package io.github.korhenon.data.impl.android

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.bind
import org.koin.dsl.module

val dataImplAndroidModule = module {
    factory {
        PackagesDataSourceImpl(androidContext())
    } bind PackagesDataSource::class
}