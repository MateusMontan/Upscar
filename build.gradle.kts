buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        // Aqui está o classpath correto para o Google Services
        classpath("com.android.tools.build:gradle:8.1.0")  // Verifique a versão adequada
        classpath("com.google.gms:google-services:4.3.15")  // Atualize para a versão recente
    }
}

plugins {
    alias(libs.plugins.android.application) apply false  // Correto para Kotlin DSL
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}
