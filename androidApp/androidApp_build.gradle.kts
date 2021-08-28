plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-parcelize")
    id("androidx.navigation.safeargs.kotlin")
}

dependencies {
    implementation(project(":samples:orbit-posts-multiplatform:shared"))
    implementation("androidx.appcompat:appcompat:1.3.1")

    implementation("org.orbit-mvi:orbit-core:4.2.0")
    implementation("org.orbit-mvi:orbit-viewmodel:4.2.0")

    implementation("io.ktor:ktor-client-android:1.6.2")

    implementation("io.coil-kt:coil-compose:1.3.2")

    // Dependency Injection
    implementation("io.insert-koin:koin-android:3.1.2")

    // UI
    implementation("androidx.constraintlayout:constraintlayout:2.1.0")
    implementation("com.google.android.material:material:1.4.0")
    implementation("androidx.navigation:navigation-fragment-ktx:2.3.5")
    implementation("androidx.navigation:navigation-ui-ktx:2.3.5")
    implementation("androidx.lifecycle:lifecycle-common-java8:2.4.0-alpha03")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.4.0-alpha03")

    // Jetpack Compose
    implementation("androidx.activity:activity-compose:1.3.1")
    implementation("androidx.compose.ui:ui:1.0.1")
    // Tooling support (Previews, etc.)
    implementation("androidx.compose.ui:ui-tooling:1.0.1")
    // Foundation (Border, Background, Box, Image, Scroll, shapes, animations, etc.)
    implementation("androidx.compose.foundation:foundation:1.0.1")
    // Material Design
    implementation("androidx.compose.material:material:1.0.1")
    // Material design icons
    implementation("androidx.compose.material:material-icons-core:1.0.1")
    // Navigation
    implementation("androidx.navigation:navigation-compose:2.4.0-alpha06")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0-alpha03")
    // Lifecycle
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:1.0.0-alpha07")

    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:1.1.5")
}

android {
    compileSdk = 30
    defaultConfig {
        applicationId = "org.orbitmvi.orbit.sample.posts.android"
        minSdk = 23
        targetSdk = 30
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }

    buildFeatures {
        viewBinding = true

        // Enables Jetpack Compose for this module
        compose = true
    }

    compileOptions {
        isCoreLibraryDesugaringEnabled = true

        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"

        // freeCompilerArgs += listOf(
        //    "-P", "plugin:androidx.compose.compiler.plugins.kotlin:suppressKotlinVersionCompatibilityCheck=true"
        // )
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.0.1"
    }
}
