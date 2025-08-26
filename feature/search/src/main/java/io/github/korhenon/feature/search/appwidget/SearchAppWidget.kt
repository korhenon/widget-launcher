package io.github.korhenon.feature.search.appwidget

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.glance.ColorFilter
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.cornerRadius
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.Box
import androidx.glance.layout.fillMaxHeight
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.height
import androidx.glance.layout.padding
import androidx.glance.unit.ColorProvider
import io.github.korhenon.feature.search.R

internal class SearchAppWidget : GlanceAppWidget() {
    private val containerColor = Color(0xCCFFFFFF)
    private val contentColor = Color(0xFF434359)
    private val height = 60.dp

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            Content()
        }
    }

    @Composable
    private fun Content() {
        Box(
            modifier = GlanceModifier
                .height(height)
                .fillMaxWidth()
                .background(containerColor)
                .cornerRadius(height / 2)
                .padding(height / 5)
        ) {
            Image(
                provider = ImageProvider(R.drawable.ic_search),
                contentDescription = "Search icon",
                colorFilter = ColorFilter.tint(ColorProvider(contentColor)),
                modifier = GlanceModifier.fillMaxHeight()
            )
        }
    }
}