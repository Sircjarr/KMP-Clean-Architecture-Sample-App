package com.sircjarr.marvelrivalsherolookup.feature_api.data_hero

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import io.ktor.utils.io.core.use
import kotlinx.io.IOException
import kotlinx.serialization.json.Json

private const val URL_HEROES_LIST = "https://mrapi.org/api/heroes"
private const val URL_HERO_DETAILS = "https://mrapi.org/api/hero/"
private const val URL_HERO_RATES = "https://mrapi.org/api/heroes-stats/pc"

class HeroDataSourceKtor: HeroDataSource {

    override suspend fun getHeroesList(): List<HeroListItemDto> {
        return try {
            buildClient().use { client ->
                client.get(URL_HEROES_LIST).body<List<HeroListItemDto>>()
            }
        } catch (e: Exception) {
            throw handleEx(e)
        }
    }

    override suspend fun getHeroDetails(name: String): HeroDetailsDto {
        val heroNameArg = name.lowercase().split(" ").map { it.replaceFirstChar { it.uppercase() } }
            .joinToString("_")

        return try {
            buildClient().use { client ->
                client.get("$URL_HERO_DETAILS$heroNameArg").body<HeroDetailsDto>()
            }
        } catch (e: Exception) {
            throw handleEx(e)
        }
    }

    override suspend fun getHeroRates(): HeroRatesDto {
        return try {
            buildClient().use { client ->
                client.get(URL_HERO_RATES).body<HeroRatesDto>()
            }
        } catch (e: Exception) {
            throw handleEx(e)
        }
    }

    private fun handleEx(e: Exception): Exception {
        e.printStackTrace()
        return IOException()
    }

    private fun buildClient(): HttpClient {
        return HttpClient(CIO) {
            install(ContentNegotiation) {
                json(Json {
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }
        }
    }
}