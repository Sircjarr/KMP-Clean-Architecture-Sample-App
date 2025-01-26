package com.sircjarr.marvelrivalsherolookup.ui.res

import androidx.compose.ui.graphics.Color

val Number.winRateColor: Color get() {
    return when(this.toFloat()) {
        in 50f..100f -> Color(0xFF008000)
        in 30f..50f -> Color(0xFFBA8E23)
        else -> Color(0xFFEB102F)
    }
}

val Number.pickRateColor: Color get() {
    return when(this.toFloat()) {
        in 12f..100f -> Color(0xFF008000)
        in 5f..12f -> Color(0xFFBA8E23)
        else -> Color(0xFFEB102F)
    }
}