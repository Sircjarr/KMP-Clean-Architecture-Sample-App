package com.sircjarr.marvelrivalsherolookup.feature.herodetails.domain

class LoadHeroDetailsUseCase(
    private val heroDetailsRepo: HeroDetailsRepo
) {
    suspend operator fun invoke(heroName: String): Result<Unit> = kotlin.runCatching {
        heroDetailsRepo.load(heroName)
    }
}