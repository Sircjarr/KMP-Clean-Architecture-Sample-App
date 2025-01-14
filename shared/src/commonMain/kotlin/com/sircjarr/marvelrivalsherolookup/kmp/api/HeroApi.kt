package com.sircjarr.marvelrivalsherolookup.kmp.api

import com.sircjarr.marvelrivalsherolookup.core.data.api.HeroDataSource
import com.sircjarr.marvelrivalsherolookup.core.data.model.HeroDetailsDto
import com.sircjarr.marvelrivalsherolookup.core.data.model.HeroListItemDto
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

class HeroApi: HeroDataSource {

    override suspend fun getHeroesList(): List<HeroListItemDto> {
        return try {
            buildClient().use { client ->
                client.get(URL_HEROES_LIST).body<List<HeroListItemDto>>().also {
                    println("response: $it")
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            throw IOException()
        }
    }

    override suspend fun getHeroDetails(name: String): HeroDetailsDto {
        TODO("Not yet implemented")
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