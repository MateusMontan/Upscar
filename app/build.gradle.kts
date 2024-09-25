plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.app.upscar"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.app.upscar"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildFeatures {
        dataBinding = true
    }

    viewBinding {
        enable = true
    }
}

buildscript {
    dependencies {
        classpath("com.google.gms:google-services:4.4.0")
    }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation("com.google.android.gms:play-services-maps:18.1.0")
    implementation(platform("com.google.firebase:firebase-bom:32.2.3"))
    implementation("com.google.firebase:firebase-database-ktx")
    implementation("com.google.firebase:firebase-auth-ktx")
    implementation("androidx.appcompat:appcompat:1.6.1")
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}

apply(plugin = "com.google.gms.google-services")