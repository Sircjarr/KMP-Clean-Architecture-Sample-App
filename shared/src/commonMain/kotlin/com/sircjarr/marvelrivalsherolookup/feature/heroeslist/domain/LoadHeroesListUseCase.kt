package com.sircjarr.marvelrivalsherolookup.feature.heroeslist.domain

import kotlinx.io.IOException

class LoadHeroesListUseCase(private val heroesListRepo: HeroesListRepo) {

    suspend operator fun invoke(): Result<Unit> = runCatching {
        heroesListRepo.loadHeroesList()
    }
}