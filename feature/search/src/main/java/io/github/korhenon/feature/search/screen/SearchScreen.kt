package io.github.korhenon.feature.search.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
internal fun SearchScreen(
    state: SearchState,
    onAction: (SearchAction) -> Unit,
    modifier: Modifier = Modifier
) {
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    Column(
        Modifier
            .then(modifier)
            .fillMaxSize()
            .padding(horizontal = 20.dp)
    ) {
        Spacer(Modifier.height(40.dp))
        TextField(
            value = state.query,
            onValueChange = { onAction(SearchAction.OnQueryChange(it)) },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                unfocusedIndicatorColor = Color.White,
                focusedIndicatorColor = Color.White
            ),
            textStyle = TextStyle(fontSize = 20.sp),
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester)
        )
        LazyColumn(verticalArrangement = Arrangement.spacedBy(5.dp)) {
            item { Spacer(Modifier.height(5.dp)) }
            items(state.searchResult) {
                val rowModifier = if (state.searchResult.size == 1) Modifier.background(
                    Color(0xFF03A9F4),
                    CircleShape
                ) else Modifier
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = rowModifier
                        .fillMaxWidth()
                        .padding(5.dp)
                        .clickable { onAction(SearchAction.OnAppSelect(it)) }
                ) {
                    if (it.icon != null) {
                        Image(
                            bitmap = it.icon!!.asImageBitmap(),
                            contentDescription = "${it.label} icon",
                            modifier = Modifier.size(55.dp)
                        )
                    } else {
                        Box(modifier = Modifier.size(55.dp))
                    }
                    Spacer(Modifier.width(20.dp))
                    Text(text = it.label, color = Color.White, fontSize = 20.sp)
                }
            }
            item { Spacer(Modifier.height(5.dp)) }
        }
    }
}