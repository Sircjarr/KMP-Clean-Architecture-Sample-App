package com.sircjarr.marvelrivalsherolookup.externalbrowserlauncher

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.net.Uri
import com.sircjarr.marvelrivalsherolookup.lib.ExternalBrowserLauncher

class ExternalBrowserLauncherAndroid(private val context: Context): ExternalBrowserLauncher {
    override fun launch(url: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url)).apply {
            flags = FLAG_ACTIVITY_NEW_TASK
        }
        context.startActivity(browserIntent)
    }
}