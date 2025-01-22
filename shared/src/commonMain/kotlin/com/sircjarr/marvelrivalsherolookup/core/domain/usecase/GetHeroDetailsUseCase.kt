package com.sircjarr.marvelrivalsherolookup.core.domain.usecase

import com.sircjarr.marvelrivalsherolookup.core.domain.repo.HeroDetailsRepo
import com.sircjarr.marvelrivalsherolookup.core.ui.model.HeroDetails
import com.sircjarr.marvelrivalsherolookup.core.ui.model.toHeroDetails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetHeroDetailsUseCase(
    private val heroDetailsRepo: HeroDetailsRepo
) {
    suspend operator fun invoke(nameArg: String): Flow<HeroDetails?> {
        return heroDetailsRepo.getFlow(nameArg).map { it?.toHeroDetails() }
    }
}