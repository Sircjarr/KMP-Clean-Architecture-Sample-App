package com.sircjarr.marvelrivalsherolookup.feature.heroeslist.domain

class LoadHeroesListUseCase(private val heroesListRepo: HeroesListRepo) {

    suspend operator fun invoke(): Result<Unit> = runCatching {
        heroesListRepo.loadHeroesList()
    }
}