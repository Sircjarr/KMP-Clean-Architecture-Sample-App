import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }
    
    sourceSets {
        androidMain.dependencies {
            implementation(projects.shared)

            // Android
            implementation(libs.androidx.lifecycle.viewmodel)

            // Compose
            implementation(libs.androidx.lifecycle.runtime.compose)
            implementation(libs.androidx.activity.compose)
            implementation(compose.preview)
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)

            // Koin
            implementation(project.dependencies.platform(libs.koin.bom))
            implementation(libs.koin.core)

            // Koin Android
            implementation(libs.koin.android)

            // Koin + Android Jetpack Compose
            implementation(libs.koin.androidx.compose)
            implementation(libs.koin.androidx.compose.navigation)
            // implementation("io.insert-koin:koin-logger-slf4j:")

            // Coil
            implementation(libs.coil.compose)
            implementation(libs.coil.network.okhttp)

            // Extra icons (for Globe / Public icon)
            implementation(libs.androidx.material.icons.extended.android)
        }
    }
}

android {
    namespace = "com.sircjarr.marvelrivalsherolookup"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.sircjarr.marvelrivalsherolookup"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    debugImplementation(compose.uiTooling)
}

