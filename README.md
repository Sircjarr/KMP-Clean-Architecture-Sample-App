![GitHub top language](https://img.shields.io/github/languages/top/Sircjarr/KMP-Clean-Architecture-Sample-App)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

Compact Kotlin Multiplatform and reactive clean architecture sample mobile app targeting both Android and iOS platforms. In this project, all code is shared between the platforms except for the native UI.

## Features
* Discover hero stats and competitive win/pick rates from the game Marvel Rivals
* Fetch hero details and multiplayer stats from a network call and display in a list categorized by class
* Click on a hero to view their lore description along with more useful stats
* Launch the device's external browser to view the hero's wiki page

## Demos
List to details | Search and filtering | Redirect to external browser
:-:|:-:|:-:
![](https://github.com/CliffJarrell/Marvel-Rivals-Hero-Lookup/blob/main/readme-res/ListToDetails.gif) | ![](https://github.com/CliffJarrell/Marvel-Rivals-Hero-Lookup/blob/main/readme-res/SearchAndFiltering.gif) | ![](https://github.com/CliffJarrell/Marvel-Rivals-Hero-Lookup/blob/main/readme-res/ToExternalBrowser.gif)

## Dependencies
 Topic | Android | iOS | KMP
:-|-:|-:|-:
Dependency injection | - | - | [Koin](https://insert-koin.io/)
Network | CIO engine | Darwin engine | [Ktor](https://ktor.io/) + kt serialization plugin
Reactive programming | - | - | [SKIE plugin](https://github.com/touchlab/SKIE)
Unit test runner | - | - | [Jetbrains kotlin-test](https://kotlinlang.org/api/core/kotlin-test/)
UI | Jetpack Compose + Compose Navigation | SwiftUI + SwiftUI Navigation | -
Image loading | [Coil-Compose](https://github.com/coil-kt/coil) AsyncImage | SwiftUI AsyncImage | -

## Architecture
![](https://github.com/CliffJarrell/Marvel-Rivals-Hero-Lookup/blob/main/readme-res/clean_arch_marvel_rivals.png)

## File structure
```
.
├── composeApp
│   └── src/androidMain/kotlin/com/sircjarr/marvelrivalsherolookup
│       ├── MainActivity.kt
│       ├── di
│       │   └── AndroidDiModule.kt
│       └── ui
│           ├── MainNavHost.kt
│           ├── composables
│           │   ├── ErrorMessageWithRetry.kt
│           │   └── LoadingMessage.kt
│           ├── res
│           │   ├── ColorRes.kt
│           │   └── RateColor.kt
│           ├── screens
│           │   ├── herodetails
│           │   │   ├── HeroDetailsAndroidViewModel.kt
│           │   │   ├── HeroDetailsContent.kt
│           │   │   ├── HeroDetailsScreen.kt
│           │   │   ├── HeroImageHeader.kt
│           │   │   └── HeroNameHeader.kt
│           │   └── heroeslist
│           │       ├── HeroesListAndroidViewModel.kt
│           │       ├── HeroesListContent.kt
│           │       ├── HeroesListScreen.kt
│           │       └── HeroesListTopBar.kt
│           └── util
│               └── TextFormatter.kt
├── iosApp
│   ├── iosApp
│       ├── MyPlayground.playground
│       ├── NavGraph.swift
│       ├── iOSApp.swift
│       ├── lib
│       │   ├── ArrayExtensions.swift
│       │   ├── CheckBox.swift
│       │   ├── ColorExtensions.swift
│       │   └── DictExtensions.swift
│       └── ui
│           ├── res
│           │   ├── ColorRes.swift
│           │   └── RateColor.swift
│           ├── screens
│           │   ├── herodetails
│           │   │   ├── HeroDetailsArgs.swift
│           │   │   ├── HeroDetailsIosViewModel.swift
│           │   │   ├── HeroDetailsScaffold.swift
│           │   │   ├── HeroDetailsScreen.swift
│           │   │   ├── HeroImageHeader.swift
│           │   │   └── HeroNameHeader.swift
│           │   └── heroeslist
│           │       ├── HeroesListContent.swift
│           │       ├── HeroesListIosViewModel.swift
│           │       ├── HeroesListScaffold.swift
│           │       ├── HeroesListScreen.swift
│           │       └── HeroesListTopBar.swift
│           └── views
│               ├── ErrorMessageWithRetry.swift
│               └── LoadingMessage.swift
└── shared
    └── src
        ├── androidMain
        │   └── kotlin/com/sircjarr/marvelrivalsherolookup
        │       ├── di
        │       │   └── IntermediateSetAndroidModule.kt
        │       └── externalbrowserlauncher
        │           └── ExternalBrowserLauncherAndroid.kt
        ├── commonMain
        │   └── kotlin/com/sircjarr/marvelrivalsherolookup
        │       ├── FakeData.kt
        │       ├── di
        │       │   └── CommonDiModule.kt
        │       ├── feature
        │       │   ├── herodetails
        │       │   │   ├── di
        │       │   │   │   └── HeroDetailsModule.kt
        │       │   │   ├── domain
        │       │   │   │   ├── GetHeroDetailsUseCase.kt
        │       │   │   │   ├── HeroDetailsModel.kt
        │       │   │   │   ├── HeroDetailsRepo.kt
        │       │   │   │   └── LoadHeroDetailsUseCase.kt
        │       │   │   └── ui
        │       │   │       ├── HeroDetails.kt
        │       │   │       ├── HeroDetailsViewModel.kt
        │       │   │       └── HeroDetailsViewState.kt
        │       │   └── heroeslist
        │       │       ├── di
        │       │       │   └── HeroesListModule.kt
        │       │       ├── domain
        │       │       │   ├── GetHeroesListUseCase.kt
        │       │       │   ├── HeroesListModel.kt
        │       │       │   ├── HeroesListRepo.kt
        │       │       │   └── LoadHeroesListUseCase.kt
        │       │       └── ui
        │       │           ├── HeroesList.kt
        │       │           ├── HeroesListViewModel.kt
        │       │           └── HeroesListViewState.kt
        │       ├── feature_api
        │       │   ├── data_hero
        │       │   │   ├── ApiDataHeroModule.kt
        │       │   │   ├── HeroDataSource.kt
        │       │   │   ├── HeroDataSourceKtor.kt
        │       │   │   ├── HeroDetailsDto.kt
        │       │   │   ├── HeroListItemDto.kt
        │       │   │   └── HeroRatesDto.kt
        │       │   └── ui
        │       │       └── Screen.kt
        │       └── lib
        │           └── ExternalBrowserLauncher.kt
        ├── commonTest
        │   └── kotlin
        │       ├── HeroDetailsRepoTest.kt
        │       └── HeroesListRepoTest.kt
        └── iosMain
            └── kotlin/com/sircjarr/marvelrivalsherolookup
                ├── di
                │   ├── IntermediateSetIosModule.kt
                │   └── KoinIosHelper.kt
                └── externalbrowserlauncher
                    └── ExternalBrowserLauncherIos.kt
```
## KMP source sets
Source set | Type | Description
:-|-:|-:
shared/commonMain | common | Shared code containing core business logic accessible by all the source sets
shared/commonTest | common | `commonMain` non-instrumented unit tests
shared/androidMain | intermediate | Android-specific implementations of `commonMain` interfaces
shared/iOSMain | intermediate | iOS-specific implementations of `commonMain` interfaces
composeApp | target | Entry point for Android application and Compose code. Depends on `androidMain`.
iOSApp | target | Entry point for iOS application and SwiftUI code. Depends on `iOSMain`.
  
## Dependency injection
Koin modules are organized by source set and feature; making them small and encapsulated. 

### Shared commonMain source set modules
```kotlin
// Feature-level modules
val apiDataHeroModule = module {
    singleOf(::HeroDataSourceKtor) bind HeroDataSource::class // Singleton
    singleOf(::HeroDataSourceKtor) { createdAtStart() }
}

val heroesListModule = module {
    singleOf(::HeroesListRepo) { createdAtStart() }
    singleOf(::GetHeroesListUseCase) { createdAtStart() }
    singleOf(::LoadHeroesListUseCase) { createdAtStart() }
    singleOf(::HeroesListViewModel) { createdAtStart() }
}

val heroDetailsModule = module {
    singleOf(::HeroDetailsRepo) { createdAtStart() }
    factoryOf(::GetHeroDetailsUseCase) // Inject on-demand
    factoryOf(::LoadHeroDetailsUseCase)
    factoryOf(::HeroDetailsViewModel)
}

// Consolidate feature modules into one
val commonDiModule = module {
    includes(apiDataHeroModule, heroesListModule, heroDetailsModule)
}
```

### Intermediate source set modules
````kotlin
// androidMain
val intermediateSetAndroidModule = module {
    factoryOf(::ExternalBrowserLauncherAndroid) bind ExternalBrowserLauncher::class
    factoryOf(::ExternalBrowserLauncherAndroid)
}

// iosMain
val intermediateSetIosModule = module {
    factoryOf(::ExternalBrowserLauncherIos) bind ExternalBrowserLauncher::class
    factoryOf(::ExternalBrowserLauncherIos)
}
````

### Android injection in composeApp
````kotlin
// On app startup
startKoin {
    androidContext(application)
    modules(commonDiModule, intermediateSetAndroidModule, composeAppModule)
}

// Additional target-level module needed for Koin to inject Anrdoid ViewModels
val composeAppModule = module {
    viewModel { HeroesListAndroidViewModel(get()) }
    viewModelOf(::HeroesListAndroidViewModel)

    viewModel { HeroDetailsAndroidViewModel(get()) }
    viewModelOf(::HeroDetailsAndroidViewModel)
}
````

### iOS injection in iosApp
````swift
// On app startup
init() {
    KoinIosHelper.companion.startKoin()
}
````
````kotlin
// Helper in iosMain; access Koin indirectly in Swift code
class KoinIosHelper {
    companion object: KoinComponent {
        fun startKoin() {
            org.koin.core.context.startKoin {
                modules(commonDiModule, intermediateSetIosModule)
            }
        }

        val heroesListViewModel: HeroesListViewModel by inject()
        val heroDetailsViewModel: HeroDetailsViewModel by inject()
    }
}
````

## Build.gradle files
1. project-level build.gradle
   * Declares all of project's plugins: KMP, SKIE, kt serialization, etc...
2. shared/build.gradle
   * Declares target source sets to instruct Kotlin to compile code for that specific target
   * Declares dependencies for `commonMain`, `commonTest`, and `iosMain` source sets
3. composeApp/build.gradle
    * For Android dependencies like a typical Android project 

## Unit Testing
Tests exist in the `commonTest` source set and use the [Jetbrains kotlin-test](https://kotlinlang.org/api/core/kotlin-test/) runner
1. [HeroDetailsRepoTest.kt](https://github.com/CliffJarrell/Marvel-Rivals-Hero-Lookup/blob/main/shared/src/commonTest/kotlin/HeroDetailsRepoTest.kt)
1. [HeroListRepoTest.kt](https://github.com/CliffJarrell/Marvel-Rivals-Hero-Lookup/blob/main/shared/src/commonTest/kotlin/HeroesListRepoTest.kt)

## Todo
- [ ] Replace or update unsupported network API
- [ ] Scope view model coroutines to Android's viewModelScope
- [ ] R8 / ProGuard for Android to remove unused icon resources imported from large materials-extended library
- [ ] Error UI for loading images
- [ ] Improve error messages for: offline, server error codes, and invalid response parsing

## Contributing
This is a project primarily for demo purposes and is not open to MRs. However, if you would like to work on top of it feel free to fork away.   

## :star: Aknowledgements
Thanks to https://lunarapi.org/ for the API.

If this project caught your interest enough to star or read all the way through — thank you!

## Disclaimer
This a non-official application and not endorsed by NetEase Games or Marvel Entertainment in any way. NetEase, Marvel Entertainment, and all associated properties are trademarks or registered trademarks of Marvel Entertainment.
