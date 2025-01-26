package com.sircjarr.marvelrivalsherolookup.ui.screens.herodetails

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage

@Composable
fun HeroNameHeader(modifier: Modifier, name: String, iconUrl: String) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = name, style = MaterialTheme.typography.h3)

        Spacer(modifier = Modifier.weight(1f))

        AsyncImage(
            modifier = Modifier.size(40.dp),
            model = iconUrl,
            contentDescription = "",
        )
    }
}