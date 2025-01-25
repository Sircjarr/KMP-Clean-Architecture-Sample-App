package com.sircjarr.marvelrivalsherolookup.feature.herodetails.ui

import com.sircjarr.marvelrivalsherolookup.feature.herodetails.domain.GetHeroDetailsUseCase
import com.sircjarr.marvelrivalsherolookup.feature.herodetails.domain.LoadHeroDetailsUseCase
import com.sircjarr.marvelrivalsherolookup.lib.ExternalBrowserLauncher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HeroDetailsViewModel(
    private val getHeroDetailsUseCase: GetHeroDetailsUseCase,
    private val loadHeroDetailsUseCase: LoadHeroDetailsUseCase,
    private val externalBrowserLauncher: ExternalBrowserLauncher
) {
    val viewState = MutableStateFlow(HeroDetailsViewState())

    // Todo: scope to viewModelScope
    private val scope = CoroutineScope(Dispatchers.IO)
    lateinit var heroName: String
    lateinit var webUrl: String

    fun init(heroName: String, webUrl: String) {
        this.heroName = heroName
        this.webUrl = webUrl

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

    fun launchHeroUrlInExternalBrowser() {
        externalBrowserLauncher.launch(webUrl)
    }
}