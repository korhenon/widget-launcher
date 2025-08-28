package io.github.korhenon.feature.search.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.korhenon.data.packages.AppInfo
import io.github.korhenon.data.packages.InstalledApps
import io.github.korhenon.data.packages.PackagesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.takeWhile
import kotlinx.coroutines.flow.transformWhile
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

internal class SearchViewModel(
    private val repository: PackagesRepository
) : ViewModel() {
    private val _state = MutableStateFlow(SearchState())
    val state = _state.onStart {}
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), SearchState())

    init {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.loadInstalledApps().takeWhile {
                    appsToState(it.applications)
                    !it.isActual
                }.collect {
                    appsToState(it.applications)
                }
            }
        }
    }

    private suspend fun appsToState(apps: List<AppInfo>) {
        _state.emit(
            _state.value.copy(
                applications = apps.sortedBy { it.label }
            ))
        onQueryChange(_state.value.query)
    }

    fun onAction(action: SearchAction) {
        when (action) {
            is SearchAction.OnQueryChange -> onQueryChange(action.value)
            is SearchAction.OnAppSelect -> onAppSelect(action.app)
        }
    }

    private fun onAppSelect(app: AppInfo) {
        repository.launchApp(app)
    }

    private fun onQueryChange(value: String) {
        viewModelScope.launch {
            _state.emit(
                _state.value.copy(
                    query = value,
                    searchResult = _state.value.applications.filter {
                        it.label.contains(
                            value,
                            ignoreCase = true
                        )
                    }
                )
            )
            if (_state.value.searchResult.size == 1) {
                delay(1000)
                onAppSelect(_state.value.searchResult[0])
            }
        }
    }
}