package com.sircjarr.marvelrivalsherolookup.ui

import androidx.compose.ui.graphics.Color

val Double.winRateColor: Color get() {
    return when(this) {
        in 50f..100f -> Color(0xFF008000)
        in 30f..50f -> Color(0xFFBA8E23)
        else -> Color(0xFFEB102F)
    }
}

val Double.pickRateColor: Color get() {
    return when(this) {
        in 12f..100f -> Color(0xFF008000)
        in 5f..12f -> Color(0xFFBA8E23)
        else -> Color(0xFFEB102F)
    }
}