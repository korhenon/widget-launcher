package io.github.korhenon.feature.search.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.addCallback
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

internal class SearchActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onBackPressedDispatcher.addCallback {
            finishAndRemoveTask()
        }
        enableEdgeToEdge()
        setContent {
            SearchRoot()
        }
    }

    override fun onStop() {
        super.onStop()
        finishAndRemoveTask()
    }
}