package com.sircjarr.marvelrivalsherolookup.di

import com.sircjarr.marvelrivalsherolookup.externalbrowserlauncher.ExternalBrowserLauncherAndroid
import com.sircjarr.marvelrivalsherolookup.lib.ExternalBrowserLauncher
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val intermediateSetAndroidModule = module {
    factoryOf(::ExternalBrowserLauncherAndroid) bind ExternalBrowserLauncher::class
    factoryOf(::ExternalBrowserLauncherAndroid)
}