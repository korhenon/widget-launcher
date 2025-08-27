package io.github.korhenon.feature.search.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.korhenon.data.packages.PackagesRepository
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

internal class SearchViewModel(
    private val repository: PackagesRepository
) : ViewModel() {
    private val _state = MutableStateFlow(SearchState())
    val state = _state.onStart {
        _state.emit(
            _state.value.copy(
                applications = repository.loadInstalledApps().applications
            )
        )
        onQueryChange(_state.value.query)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), SearchState())


    fun onAction(action: SearchAction) {
        when (action) {
            is SearchAction.OnQueryChange -> onQueryChange(action.value)
        }
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
        }
    }
}