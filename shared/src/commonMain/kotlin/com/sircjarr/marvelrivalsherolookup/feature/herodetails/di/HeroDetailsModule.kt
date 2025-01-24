package com.sircjarr.marvelrivalsherolookup.feature.herodetails.di

import com.sircjarr.marvelrivalsherolookup.feature.herodetails.domain.GetHeroDetailsUseCase
import com.sircjarr.marvelrivalsherolookup.feature.herodetails.domain.HeroDetailsRepo
import com.sircjarr.marvelrivalsherolookup.feature.herodetails.domain.LoadHeroDetailsUseCase
import com.sircjarr.marvelrivalsherolookup.feature.herodetails.ui.HeroDetailsViewModel
import org.koin.core.module.dsl.createdAtStart
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val heroDetailsModule = module {
    singleOf(::HeroDetailsRepo) { createdAtStart() }
    factoryOf(::GetHeroDetailsUseCase) // Inject on-demand
    factoryOf(::LoadHeroDetailsUseCase)
    factoryOf(::HeroDetailsViewModel)
}