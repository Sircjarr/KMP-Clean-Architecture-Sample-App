package com.sircjarr.marvelrivalsherolookup.core.ui.screens.herodetails

import com.sircjarr.marvelrivalsherolookup.core.domain.usecase.GetHeroDetailsUseCase
import com.sircjarr.marvelrivalsherolookup.core.domain.usecase.LoadHeroDetailsUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HeroDetailsViewModel(
    private val getHeroDetailsUseCase: GetHeroDetailsUseCase,
    private val loadHeroDetailsUseCase: LoadHeroDetailsUseCase
) {
    val viewState = MutableStateFlow(HeroDetailsViewState())

    // Todo: scope to viewModelScope
    private val scope = CoroutineScope(Dispatchers.IO)
    lateinit var heroName: String

    fun init(heroName: String) {
        this.heroName = heroName

        scope.launch(Dispatchers.IO) {
            getHeroDetailsUseCase(heroName).collect { details ->
                details?.let {
                    withContext(Dispatchers.Main) {
                        viewState.value = viewState.value.copy(
                            isLoading = false,
                            details
                        )
                    }
                }
            }
        }

        loadHeroDetails(heroName)
    }

    fun loadHeroDetails(name: String) {
        viewState.value = viewState.value.copy(
            isLoading = true
        )

        scope.launch(Dispatchers.IO) {
            loadHeroDetailsUseCase(name)
        }
    }
}