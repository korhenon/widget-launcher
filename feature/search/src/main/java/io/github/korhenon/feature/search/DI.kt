package io.github.korhenon.feature.search

import io.github.korhenon.data.packages.dataPackagesModule
import io.github.korhenon.feature.search.screen.SearchViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val featureSearchModule = module {
    includes(dataPackagesModule)
    viewModelOf(::SearchViewModel)
}