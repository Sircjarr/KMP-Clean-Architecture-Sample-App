package com.sircjarr.marvelrivalsherolookup.core.ui.screens.herodetails

import com.sircjarr.marvelrivalsherolookup.core.domain.usecase.GetHeroDetailsUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HeroDetailsViewModel(
    private val getHeroDetailsUseCase: GetHeroDetailsUseCase
) {
    val viewState = MutableStateFlow(HeroDetailsViewState())

    // Todo: scope to viewModelScope
    private val scope = CoroutineScope(Dispatchers.IO)
    lateinit var name: String

    fun init(name: String) {
        this.name = name
        loadHeroDetails(name)
    }

    fun loadHeroDetails(name: String) {
        viewState.value = viewState.value.copy(
            isLoading = true
        )

        scope.launch(Dispatchers.IO) {
            val hero = getHeroDetailsUseCase(name)

            withContext(Dispatchers.Main) {
                viewState.value = viewState.value.copy(
                    isLoading = false,
                    hero
                )
            }
        }
    }
}