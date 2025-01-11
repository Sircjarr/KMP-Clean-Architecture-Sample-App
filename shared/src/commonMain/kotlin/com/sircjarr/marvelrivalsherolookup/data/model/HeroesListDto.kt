package com.sircjarr.marvelrivalsherolookup.data.model

data class HeroesListDto(
    val list: List<HeroDetailDto>
)

data class HeroDetailDto(
    val id: String,
    val tag: String,
    val title: String,
    val urls: List<String>,
    val url: String
)