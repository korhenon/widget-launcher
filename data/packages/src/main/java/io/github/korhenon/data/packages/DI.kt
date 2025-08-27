package io.github.korhenon.data.packages

import io.github.korhenon.data.impl.android.dataImplAndroidModule
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val dataPackagesModule = module {
    includes(dataImplAndroidModule)
    singleOf(::PackagesRepositoryImpl) bind PackagesRepository::class
}