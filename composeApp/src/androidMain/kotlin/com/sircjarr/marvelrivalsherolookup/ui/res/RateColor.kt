package com.sircjarr.marvelrivalsherolookup.ui.res

import androidx.compose.ui.graphics.Color

val Number.winRateColor: Color get() {
    return when(this.toFloat()) {
        in 50f..100f -> ColorRes.green
        in 30f..50f -> ColorRes.yellow
        else -> ColorRes.red
    }
}

val Number.pickRateColor: Color get() {
    return when(this.toFloat()) {
        in 12f..100f -> ColorRes.green
        in 5f..12f -> ColorRes.yellow
        else -> ColorRes.red
    }
}