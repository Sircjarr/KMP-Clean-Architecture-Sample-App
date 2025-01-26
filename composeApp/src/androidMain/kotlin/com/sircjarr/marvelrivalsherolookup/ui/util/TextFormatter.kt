package com.sircjarr.marvelrivalsherolookup.ui.util

fun String.pascalCase(): String {
    return lowercase().split(" ")
        .joinToString(" ") { it.replaceFirstChar { it.uppercase() } }
}