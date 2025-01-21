package com.sircjarr.marvelrivalsherolookup.ui.screens.herodetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.sircjarr.marvelrivalsherolookup.core.ui.screens.herodetails.HeroDetailsViewModel

class HeroDetailsAndroidViewModel(
    private val savedStateHandle: SavedStateHandle,
    val viewModel: HeroDetailsViewModel
): ViewModel()