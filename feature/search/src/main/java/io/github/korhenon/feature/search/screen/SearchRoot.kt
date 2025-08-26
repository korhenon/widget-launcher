package io.github.korhenon.feature.search.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun SearchRoot() {
    val viewModel = koinViewModel<SearchViewModel>()
    val state by viewModel.state.collectAsState()

    Scaffold(containerColor = Color.Transparent) { innerPaddings ->
        SearchScreen(
            state = state,
            onAction = viewModel::onAction,
            modifier = Modifier.padding(innerPaddings)
        )
    }
}