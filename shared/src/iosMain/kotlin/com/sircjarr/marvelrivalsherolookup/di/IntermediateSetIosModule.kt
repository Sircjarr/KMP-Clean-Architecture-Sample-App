package com.sircjarr.marvelrivalsherolookup.di

import com.sircjarr.marvelrivalsherolookup.externalbrowserlauncher.ExternalBrowserLauncherIos
import com.sircjarr.marvelrivalsherolookup.lib.ExternalBrowserLauncher
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val intermediateSetIosModule = module {
    factoryOf(::ExternalBrowserLauncherIos) bind ExternalBrowserLauncher::class
    factoryOf(::ExternalBrowserLauncherIos)
}