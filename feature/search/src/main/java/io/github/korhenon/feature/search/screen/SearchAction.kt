package io.github.korhenon.feature.search.screen

import io.github.korhenon.data.packages.AppInfo

internal sealed interface SearchAction {
    data class OnQueryChange(val value: String) : SearchAction
    data class OnAppSelect(val app: AppInfo) : SearchAction
}