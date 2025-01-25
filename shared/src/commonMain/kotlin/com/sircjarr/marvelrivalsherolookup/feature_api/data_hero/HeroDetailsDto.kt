package com.sircjarr.marvelrivalsherolookup.feature_api.data_hero

import kotlinx.serialization.Serializable

@Serializable
data class HeroDetailsDto(
    val title: String,
    val name: String,
    val tag: String,
    val description: String,
    val images: List<String>,
    val stats: List<HeroStatDto>
)

@Serializable
data class HeroStatDto(
    val title: String,
    val value: String
)