package com.sircjarr.marvelrivalsherolookup.feature.herodetails.ui

import com.sircjarr.marvelrivalsherolookup.FakeData

data class HeroDetailsViewState(
    val isLoading: Boolean = true,
    val err: String? = null,
    val heroDetails: HeroDetails? = null
)

val FakeData.Companion.heroDetailsViewState
    get() = HeroDetailsViewState(
        isLoading = false,
        heroDetails = HeroDetails(
            name = "Iron Man",
            realName = "Anthony \"Tony\" Stark",
            classification = "DUELIST",
            description = "Armed with superior intellect and a nanotech battlesuit of his own design, Tony Stark stands alongside gods as the Invincible Iron Man. His state of the art armor turns any battlefield into his personal playground, allowing him to steal the spotlight he so desperately desires.",
            imageUrl = "",
            iconUrl = "",
            stats = listOf(
                HeroStat("Health", "250"),
                HeroStat("Movement Speed", "6 m/s"),
                HeroStat("Movement Mode", "Flight"),
            )
        )
    )