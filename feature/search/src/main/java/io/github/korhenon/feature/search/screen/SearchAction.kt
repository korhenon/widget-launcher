package io.github.korhenon.feature.search.screen

internal sealed interface SearchAction {
    data class OnQueryChange(val value: String) : SearchAction
}