package com.sircjarr.marvelrivalsherolookup.ui.screens.heroeslist

import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val composeAppModule = module {
    viewModel { HeroesListAndroidViewModel(get()) }
    viewModelOf(::HeroesListAndroidViewModel)
}