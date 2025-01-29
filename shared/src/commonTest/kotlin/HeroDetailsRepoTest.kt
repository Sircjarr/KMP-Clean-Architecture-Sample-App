import com.sircjarr.marvelrivalsherolookup.feature.herodetails.domain.HeroDetailsModel
import com.sircjarr.marvelrivalsherolookup.feature.herodetails.domain.HeroDetailsRepo
import com.sircjarr.marvelrivalsherolookup.feature.herodetails.domain.HeroStatModel
import com.sircjarr.marvelrivalsherolookup.feature_api.data_hero.HeroDataSource
import com.sircjarr.marvelrivalsherolookup.feature_api.data_hero.HeroDetailsDto
import com.sircjarr.marvelrivalsherolookup.feature_api.data_hero.HeroListItemDto
import com.sircjarr.marvelrivalsherolookup.feature_api.data_hero.HeroRatesDto
import com.sircjarr.marvelrivalsherolookup.feature_api.data_hero.HeroStatDto
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

class HeroDetailsRepoTest {
    private val fakeHeroDetailsDto = HeroDetailsDto(
        "Wolverine",
        "Logan",
        "DUELIST",
        "scary",
        listOf("", "", "img_2_url", "", "", "", "", "img_7_url"),
        listOf(HeroStatDto("Health", "350"), HeroStatDto("Movement Speed", "7m/s"))
    )

    private val fakeHeroDetailsModel = HeroDetailsModel(
        "Wolverine",
        "Logan",
        "DUELIST",
        "scary",
        "img_2_url",
        "img_7_url",
        listOf(
            HeroStatModel("Health", "350"),
            HeroStatModel("Movement Speed", "7m/s"),
        )
    )

    private var numApiCalls = 0
    private val fakeApi = object : HeroDataSource {
        override suspend fun getHeroDetails(name: String): HeroDetailsDto {
            numApiCalls ++
            return fakeHeroDetailsDto
        }
        override suspend fun getHeroesList(): List<HeroListItemDto> = error("Not implemented")
        override suspend fun getHeroRates(): HeroRatesDto = error("Not implemented")
    }

    private val target = HeroDetailsRepo(fakeApi)

    @Test
    fun returnsMergedHeroListItemModel() = runTest {
        val flow = target.getFlow("Wolverine")
        val x = flow.first()
        assertEquals(null, x)

        target.load("Wolverine")
        val y = flow.first()
        assertEquals(fakeHeroDetailsModel, y)
    }

    @Test
    fun successfullyCachesApiResponses() = runTest {
        target.load("Wolverine")
        assertEquals(1, numApiCalls)

        target.load("Wolverine")
        assertEquals(1, numApiCalls)

        target.load("Mantis")
        assertEquals(2, numApiCalls)
    }
}