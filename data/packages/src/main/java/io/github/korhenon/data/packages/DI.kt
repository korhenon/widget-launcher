package io.github.korhenon.data.packages

import io.github.korhenon.data.impl.android.dataImplAndroidModule
import io.github.korhenon.data.impl.room.dataImplRoomModule
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val dataPackagesModule = module {
    includes(dataImplAndroidModule)
    includes(dataImplRoomModule)
    singleOf(::PackagesRepositoryImpl) bind PackagesRepository::class
}