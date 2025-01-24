package com.sircjarr.marvelrivalsherolookup.feature.heroeslist.domain

import com.sircjarr.marvelrivalsherolookup.feature.heroeslist.ui.HeroListItem
import com.sircjarr.marvelrivalsherolookup.feature.heroeslist.ui.toHeroesList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetHeroesListUseCase(private val heroesListRepo: HeroesListRepo) {

    operator fun invoke(): Flow<List<HeroListItem>?> {
        return heroesListRepo.getFlow().map {
            it?.toHeroesList()
        }
    }
}