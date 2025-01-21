package com.sircjarr.marvelrivalsherolookup.core.domain.usecase

import com.sircjarr.marvelrivalsherolookup.core.domain.repo.HeroDetailsRepo
import com.sircjarr.marvelrivalsherolookup.core.ui.model.HeroDetails
import com.sircjarr.marvelrivalsherolookup.core.ui.model.toHeroDetails

class GetHeroDetailsUseCase(
    private val heroDetailsRepo: HeroDetailsRepo
) {

    suspend operator fun invoke(nameArg: String): HeroDetails {
        return if (heroDetailsRepo.hasCached(nameArg)) {
            heroDetailsRepo.get(nameArg)
        } else {
            heroDetailsRepo.load(nameArg)
        }.toHeroDetails()
    }
}