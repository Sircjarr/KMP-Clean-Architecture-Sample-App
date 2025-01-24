package com.sircjarr.marvelrivalsherolookup

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import com.sircjarr.marvelrivalsherolookup.di.commonDiModule
import com.sircjarr.marvelrivalsherolookup.di.composeAppModule
import com.sircjarr.marvelrivalsherolookup.ui.MainNavHost
import org.koin.core.context.GlobalContext.startKoin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inject dependencies per module definitions
        startKoin {
            modules(commonDiModule, composeAppModule)
        }

        setContent {
            MaterialTheme {
                MainNavHost()
            }
        }
    }
}