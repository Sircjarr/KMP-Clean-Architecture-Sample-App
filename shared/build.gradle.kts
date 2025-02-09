import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    kotlin("plugin.serialization") version "1.9.21"
    alias(libs.plugins.skie)
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            // Name of module to be imported in Xcode
            baseName = "Shared"

            /**
             * Static: Binary content of your shared lib is merged inside your iOS binary app
             * Dynamic: Binary content of your shared lib is loaded when the app is started, as an
             * an external file
             *
             * Setting to dynamic fixed Xcode SwiftUI previews, but broke importing the Shared module
             * Setting to static fixes the Shared module import, but breaks the previews
             */
            isStatic = false
        }
    }

    sourceSets {
        commonMain.dependencies {
            // KT coroutines
            implementation(libs.kotlinx.coroutines.core)

            // Koin
            implementation(project.dependencies.platform(libs.koin.bom))
            implementation(libs.koin.core)

            // Ktor
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.cio) // Engine for Android

            // Ktor JSON DTO Serialization
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.serialization.kotlinx.json)

            // KT serialization
            implementation(libs.kotlinx.serialization.json)
        }

        commonTest.dependencies {
            implementation(libs.kotlin.test)
            implementation(libs.kotlinx.coroutines.test)
        }

        iosMain.dependencies {
            implementation(project.dependencies.platform(libs.koin.bom))
            implementation(libs.koin.core)
            implementation(libs.ktor.client.darwin) // Engine for iOS
        }
    }
}

android {
    namespace = "com.sircjarr.marvelrivalsherolookup.shared"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}
