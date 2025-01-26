package com.sircjarr.marvelrivalsherolookup.feature.herodetails.domain

import com.sircjarr.marvelrivalsherolookup.feature.herodetails.ui.HeroDetails
import com.sircjarr.marvelrivalsherolookup.feature.herodetails.ui.toHeroDetails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetHeroDetailsUseCase(
    private val heroDetailsRepo: HeroDetailsRepo
) {
    operator fun invoke(nameArg: String): Flow<HeroDetails?> {
        return heroDetailsRepo.getFlow(nameArg).map { it?.toHeroDetails() }
    }
}