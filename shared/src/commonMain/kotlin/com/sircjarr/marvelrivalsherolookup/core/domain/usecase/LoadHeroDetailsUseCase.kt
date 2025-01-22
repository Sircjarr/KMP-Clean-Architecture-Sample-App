package com.sircjarr.marvelrivalsherolookup.core.domain.usecase

import com.sircjarr.marvelrivalsherolookup.core.domain.repo.HeroDetailsRepo

class LoadHeroDetailsUseCase(
    private val heroDetailsRepo: HeroDetailsRepo
) {
    operator suspend fun invoke(heroName: String) {
        heroDetailsRepo.load(heroName)
    }
}