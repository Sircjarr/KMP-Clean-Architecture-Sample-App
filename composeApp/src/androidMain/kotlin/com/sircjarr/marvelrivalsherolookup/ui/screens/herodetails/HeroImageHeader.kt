package com.sircjarr.marvelrivalsherolookup.ui.screens.herodetails

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Public
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage

@Composable
fun HeroImageHeader(modifier: Modifier, imageUrl: String, onGlobeIconClicked: () -> Unit) {
    Box(
        modifier = modifier
            .background(color = Color.Black)
    ) {
        AsyncImage(
            model = imageUrl,
            contentScale = ContentScale.Inside,
            contentDescription = null
        )

        Icon(
            modifier = Modifier
                .padding(8.dp)
                .clickable { onGlobeIconClicked() }
                .size(40.dp)
                .align(Alignment.TopEnd),
            imageVector = Icons.Default.Public,
            contentDescription = "",
            tint = Color.DarkGray
        )
    }
}