package com.sircjarr.marvelrivalsherolookup.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.sircjarr.marvelrivalsherolookup.ui.res.ColorRes

@Composable
fun LoadingMessage(modifier: Modifier = Modifier, msg: String) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(color = ColorRes.marvelRed)
        Text(msg)
    }
}