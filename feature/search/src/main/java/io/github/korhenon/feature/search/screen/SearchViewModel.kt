package io.github.korhenon.feature.search.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

internal class SearchViewModel : ViewModel() {
    private val _state = MutableStateFlow(SearchState())
    val state = _state.asStateFlow()

    fun onAction(action: SearchAction) {
        when (action) {
            is SearchAction.OnQueryChange -> onQueryChange(action.value)
        }
    }

    private fun onQueryChange(value: String) {
        viewModelScope.launch {
            _state.emit(_state.value.copy(query = value))
        }
    }
}