//buildscript {
//    repositories {
//        google()
//        mavenCentral()
//        mavenLocal()
//    }
//
//    dependencies {
//
//    }
//}

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("maven-publish")
}

android {
    namespace = "ru.wb.debugscreen"
    compileSdk = 34

    defaultConfig {
        minSdk = 21

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            consumerProguardFiles("consumer-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
}

java {
    toolchain {
        version = JavaLanguageVersion.of(17)
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {

    implementation("androidx.compose.material3:material3-android:1.2.0-alpha10")

    implementation("com.squareup.retrofit2:retrofit:2.9.0")

    implementation("com.google.code.gson:gson:2.10.1")

    implementation("androidx.room:room-ktx:2.6.0")
    ksp("androidx.room:room-compiler:2.6.0")
}

afterEvaluate {
    publishing {
        publications
            .create<MavenPublication>("DebugScreenRelease") {
                groupId = "com.github.dmitryskor99"
                artifactId = "debugscreen"
                version = "1.0.0"

                from(components["release"])
            }
    }
}