package com.sircjarr.marvelrivalsherolookup.di

import com.sircjarr.marvelrivalsherolookup.feature.herodetails.ui.HeroDetailsViewModel
import com.sircjarr.marvelrivalsherolookup.feature.heroeslist.ui.HeroesListViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

// Access Koin indirectly in Swift code
class KoinIosHelper {
    companion object: KoinComponent {
        fun startKoin() {
            org.koin.core.context.startKoin {
                modules(commonDiModule, intermediateSetIosModule)
            }
        }

        val heroesListViewModel: HeroesListViewModel by inject()
        val heroDetailsViewModel: HeroDetailsViewModel by inject()
    }
}