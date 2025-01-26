package com.sircjarr.marvelrivalsherolookup.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview

@Composable
@Preview(showBackground = true)
fun ErrorMessageWithRetry(
    modifier: Modifier = Modifier,
    msg: String = "Failed to load hero list. Please try again.",
    onRetryButtonClicked: () -> Unit = {}
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(msg, textAlign = TextAlign.Center)
        Button(onClick = onRetryButtonClicked, content = {
            Text("Retry")
        })
    }
}