package com.sircjarr.marvelrivalsherolookup.core.data.model

import kotlinx.serialization.Serializable

@Serializable
data class HeroListItemDto(
    val id: String,
    val tag: String,
    val title: String,
    val images: List<String>,
    val url: String
)