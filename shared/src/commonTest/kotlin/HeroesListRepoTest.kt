import com.sircjarr.marvelrivalsherolookup.feature.heroeslist.domain.HeroListItemModel
import com.sircjarr.marvelrivalsherolookup.feature.heroeslist.domain.HeroesListRepo
import com.sircjarr.marvelrivalsherolookup.feature_api.data_hero.HeroDataSource
import com.sircjarr.marvelrivalsherolookup.feature_api.data_hero.HeroDetailsDto
import com.sircjarr.marvelrivalsherolookup.feature_api.data_hero.HeroListItemDto
import com.sircjarr.marvelrivalsherolookup.feature_api.data_hero.HeroRateDto
import com.sircjarr.marvelrivalsherolookup.feature_api.data_hero.HeroRatesDto
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

class HeroesListRepoTest {

    private val fakeHeroListItemDtos = listOf(
        HeroListItemDto(
            "01",
            "DUELIST",
            "Iron Man",
            listOf("img_one_url", "img_two_url"),
            "hero_url"
        )
    )

    private val fakeHeroRatesDto = HeroRatesDto(
        listOf(HeroRateDto("Iron Man", "DUELIST", "10.0", "11.0"))
    )

    private val fakeApi = object : HeroDataSource {
        override suspend fun getHeroesList(): List<HeroListItemDto> = fakeHeroListItemDtos
        override suspend fun getHeroRates(): HeroRatesDto = fakeHeroRatesDto
        override suspend fun getHeroDetails(name: String): HeroDetailsDto = error("Not implemented")
    }

    private val target = HeroesListRepo(fakeApi)

    @Test
    fun returnsMergedHeroListItemModel() = runTest {
        target.loadHeroesList()

        val actual = target.getFlow().first()!!.first()

        val expected = HeroListItemModel(
            "01",
            "DUELIST",
            "Iron Man",
            "img_one_url",
            "hero_url",
            10.0,
            11.0
        )

        assertEquals(expected, actual)
    }
}