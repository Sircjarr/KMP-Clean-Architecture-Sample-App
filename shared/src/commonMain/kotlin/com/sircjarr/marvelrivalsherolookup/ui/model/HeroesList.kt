package com.sircjarr.marvelrivalsherolookup.ui.model

import com.sircjarr.marvelrivalsherolookup.domain.model.HeroesListModel

data class HeroesList(
    val list: List<HeroDetail>
)

data class HeroDetail(
    val id: String,
    val tag: String,
    val title: String,
    val urls: List<String>,
    val url: String
)

fun HeroesListModel.toHeroesList() = HeroesList(
    list.map { hero ->
        val (id, tag, title, urls, url) = hero
        HeroDetail(id, tag, title, urls, url)
    }
)