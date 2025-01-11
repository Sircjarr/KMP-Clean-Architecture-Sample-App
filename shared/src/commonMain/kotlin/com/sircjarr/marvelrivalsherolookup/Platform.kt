package com.sircjarr.marvelrivalsherolookup

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform