package com.sircjarr.marvelrivalsherolookup.core.domain.usecase

import com.sircjarr.marvelrivalsherolookup.core.domain.repo.HeroesListRepo

class LoadHeroesListUseCase(private val heroesListRepo: HeroesListRepo) {

    suspend operator fun invoke() {
        heroesListRepo.loadHeroesList()
    }
}