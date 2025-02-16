# Description
Compact Kotlin Multiplatform and reactive clean architecture sample mobile app. 
In this project, all code is shared between the platforms except for the native UI. This design facillitates maximum scalability and rapid development when building for both Android and iOS platforms.

> [!NOTE]
> This project depends on a network API that may change in the future; impacting functionality

# Demos
List to details | Search and filtering | Redirect to external browser
:-:|:-:|:-:
![](https://github.com/CliffJarrell/Marvel-Rivals-Hero-Lookup/blob/main/readme-res/ListToDetails.gif) | ![](https://github.com/CliffJarrell/Marvel-Rivals-Hero-Lookup/blob/main/readme-res/SearchAndFiltering.gif) | ![](https://github.com/CliffJarrell/Marvel-Rivals-Hero-Lookup/blob/main/readme-res/ToExternalBrowser.gif)

# Libraries
 Topic | Android | iOS | KMP
:-|-:|-:|-:
Dependency injection | - | - | [Koin](https://insert-koin.io/)
UI | Jetpack Compose + Compose Navigation | SwiftUI + SwiftUI Navigation | -
Image loading | [Coil-Compose](https://github.com/coil-kt/coil) AsyncImage | SwiftUI AsyncImage | -
Network | CIO engine | Darwin engine | [Ktor](https://ktor.io/) + kt serialization plugin
Reactive programming | - | - | [SKIE plugin](https://github.com/touchlab/SKIE)
Unit test runner | - | - | [Jetbrains kotlin-test](https://kotlinlang.org/api/core/kotlin-test/)

# Architecture

# File structure
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
        │       └── ExternalBrowserLauncherAndroid.kt
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
#### Source sets
Source set | Type | Description
:-|-:|-:
shared/commonMain | common | Shared code accessible by all the source sets. Interfaces for platform-specific source sets to implement.
shared/commonTest | common | `commonMain` non-instrumented unit tests
shared/androidMain | intermediate | Android-specific implementations of `commonMain` interfaces
shared/iOSMain | intermediate | iOS-specific implementations of `commonMain` interfaces
composeApp | target | Entry point for Android application and Compose code. Depends on `androidMain`.
iOSApp | target | Entry point for iOS application and SwiftUI code. Depends on `iOSMain`.

#### commonMain file structure
`commonMain` hosts the core business logic consumed by all other source sets and is structured with 4 root folders:
* di
  * Contains a Koin module defining injection of all shared dependencies
* lib
  * Contains reusable interfaces that are not related to business logic for intermediate source sets to implement
* feature
  * Contains one folder per feature in the application
    * This folder then may contain up to 4 folders organizing files by di and architecture layer
  * Each feature is encapsulated and does not depend on another
* feature_api
  * Contains files that are consumed by more than one 'feature'
  * In this case, I've opted to create a single `HeroDataSource` API and that is used by both `heroeslist` and `herodetails` features

# Dependency injection

# Unit Testing
Tests exist in the `commonTest` source set and use the [Jetbrains kotlin-test](https://kotlinlang.org/api/core/kotlin-test/) runner
1. [HeroDetailsRepoTest.kt](https://github.com/CliffJarrell/Marvel-Rivals-Hero-Lookup/blob/main/shared/src/commonTest/kotlin/HeroDetailsRepoTest.kt)
1. [HeroListRepoTest.kt](https://github.com/CliffJarrell/Marvel-Rivals-Hero-Lookup/blob/main/shared/src/commonTest/kotlin/HeroesListRepoTest.kt)

# Todo
- [ ] Replace or update unsupported network API
- [ ] Scope view model coroutines to Android's viewModelScope
- [ ] R8 / ProGuard for Android to remove unused icon resources imported from large materials-extended library
- [ ] Error UI for loading images
- [ ] Improve error messages for: offline, server error codes, and invalid response parsing

# API
https://lunarapi.org/

# Buy me a Coffee
If you gained any value from this, I'd appreciate it :)

# Disclaimer
This a non-official application and not endorsed by NetEase Games or Marvel Entertainment in any way. NetEase, Marvel Entertainment, and all associated properties are trademarks or registered trademarks of Marvel Entertainment.
