plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    kotlin("kapt")
    id("com.android.library")
    id("kotlin-parcelize")
    kotlin("plugin.serialization") version "1.5.21"
    id("org.orbit-mvi.orbit.swift") version "0.1.0"
}

// CocoaPods requires the podspec to have a version.
version = "1.0"

kotlin {
    android()
    ios()

    cocoapods {
        authors = "Mikołaj Leszczyński & Appmattus Limited"
        license = "Apache License, Version 2.0"
        summary = "Orbit Multiplatform Posts"

        // Configure fields required by CocoaPods.
        homepage = "Link to a Kotlin/Native module homepage"
        frameworkName = "shared"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosApp/Podfile")
    }

    sourceSets {

        @Suppress("UnusedPrivateMember")
        val commonMain by getting {
            dependencies {
                api("org.orbit-mvi:orbit-core:4.2.0")
                api("dev.icerock.moko:mvvm-core:0.11.0") // only ViewModel, EventsDispatcher, Dispatchers.UI
                implementation("dev.icerock.moko:parcelize:0.7.1")

                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.2.2")

                implementation("io.ktor:ktor-client-core:1.6.3")
                implementation("io.ktor:ktor-client-serialization:1.6.3")

                implementation("io.insert-koin:koin-core:3.1.2")
            }
        }
        @Suppress("UnusedPrivateMember")
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        @Suppress("UnusedPrivateMember")
        val androidMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-android:1.6.3")

                implementation("org.orbit-mvi:orbit-viewmodel:4.2.0")

                implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.0-alpha03")

                // Dependency Injection
                implementation("io.insert-koin:koin-android:3.1.2")
            }
        }
        @Suppress("UnusedPrivateMember")
        val androidTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation("junit:junit:4.13.2")
            }
        }
        @Suppress("UnusedPrivateMember")
        val iosMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-ios:1.6.3")
            }
        }
        @Suppress("UnusedPrivateMember")
        val iosTest by getting
    }
}

android {
    compileSdk = 30
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 23
        targetSdk = 30
    }
}
