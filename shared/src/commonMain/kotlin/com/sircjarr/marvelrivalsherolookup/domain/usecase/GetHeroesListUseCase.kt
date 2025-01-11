package com.sircjarr.marvelrivalsherolookup.domain.usecase

import com.sircjarr.marvelrivalsherolookup.domain.repo.HeroesListRepo
import com.sircjarr.marvelrivalsherolookup.ui.model.HeroesList
import com.sircjarr.marvelrivalsherolookup.ui.model.toHeroesList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetHeroesListUseCase(private val heroesListRepo: HeroesListRepo) {

    operator fun invoke(): Flow<HeroesList?> {
        return heroesListRepo.getFlow().map {
            it?.toHeroesList()
        }
    }
}