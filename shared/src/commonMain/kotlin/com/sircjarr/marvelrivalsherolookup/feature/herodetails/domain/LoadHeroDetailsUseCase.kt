package com.sircjarr.marvelrivalsherolookup.feature.herodetails.domain

class LoadHeroDetailsUseCase(
    private val heroDetailsRepo: HeroDetailsRepo
) {
    operator suspend fun invoke(heroName: String) {
        heroDetailsRepo.load(heroName)
    }
}