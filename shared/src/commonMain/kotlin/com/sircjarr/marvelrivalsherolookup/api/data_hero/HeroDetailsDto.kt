package com.sircjarr.marvelrivalsherolookup.api.data_hero

import kotlinx.serialization.Serializable

@Serializable
data class HeroDetailsDto(
    val title: String,
    val name: String,
    val tag: String,
    val description: String,
    val images: List<String>,
)