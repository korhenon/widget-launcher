package io.github.korhenon.feature.search.screen

import android.app.Application
import io.github.korhenon.data.packages.AppInfo

internal data class SearchState(
    val query: String = "",
    val searchResult: List<AppInfo> = emptyList(),
    val applications: List<AppInfo> = emptyList()
)
