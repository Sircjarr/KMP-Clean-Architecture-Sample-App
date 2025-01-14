package com.sircjarr.marvelrivalsherolookup.core.domain.usecase

import com.sircjarr.marvelrivalsherolookup.core.domain.repo.HeroesListRepo
import com.sircjarr.marvelrivalsherolookup.core.ui.model.HeroListItem
import com.sircjarr.marvelrivalsherolookup.core.ui.model.toHeroesList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetHeroesListUseCase(private val heroesListRepo: HeroesListRepo) {

    operator fun invoke(): Flow<List<HeroListItem>?> {
        return heroesListRepo.getFlow().map {
            it?.toHeroesList()
        }
    }
}