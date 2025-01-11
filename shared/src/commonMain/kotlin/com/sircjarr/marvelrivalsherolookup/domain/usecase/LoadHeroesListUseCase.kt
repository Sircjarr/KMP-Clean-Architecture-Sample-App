package com.sircjarr.marvelrivalsherolookup.domain.usecase

import com.sircjarr.marvelrivalsherolookup.domain.repo.HeroesListRepo

class LoadHeroesListUseCase(private val heroesListRepo: HeroesListRepo) {

    operator fun invoke() {
        heroesListRepo.getFlow()
    }
}