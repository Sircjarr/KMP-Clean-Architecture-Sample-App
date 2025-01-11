package com.sircjarr.marvelrivalsherolookup.domain.model

import com.sircjarr.marvelrivalsherolookup.data.model.HeroesListDto

data class HeroesListModel(
    val list: List<HeroDetailModel>
)

data class HeroDetailModel(
    val id: String,
    val tag: String,
    val title: String,
    val urls: List<String>,
    val url: String
)

fun HeroesListDto.toHeroesListModel() = HeroesListModel(
    list.map { hero ->
        val (id, tag, title, urls, url) = hero
        HeroDetailModel(id, tag, title, urls, url)
    }
)