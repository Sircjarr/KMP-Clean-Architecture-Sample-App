package com.sircjarr.marvelrivalsherolookup.data.model

import kotlinx.serialization.Serializable

@Serializable
data class HeroesListDto(
    val list: List<HeroDetailDto>
)

@Serializable
data class HeroDetailDto(
    val id: String,
    val tag: String,
    val title: String,
    val urls: List<String>,
    val url: String
)