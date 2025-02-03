package com.sircjarr.marvelrivalsherolookup.externalbrowserlauncher

import com.sircjarr.marvelrivalsherolookup.lib.ExternalBrowserLauncher
import platform.Foundation.NSURL
import platform.UIKit.UIApplication

class ExternalBrowserLauncherIos: ExternalBrowserLauncher {
    override fun launch(url: String) {
        val nsUrl = NSURL(string = url)
        UIApplication.sharedApplication().openURL(nsUrl)
    }
}