buildscript {
    repositories {
        mavenLocal()
    }
}

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
//    id("com.google.devtools.ksp")
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
//
//    ksp {
//        arg("room.schemaLocation", "$projectDir/schemas")
//        arg("room.incremental", "true")
//    }

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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation("androidx.compose.material3:material3:1.2.0-alpha10")

    implementation("com.squareup.retrofit2:retrofit:2.9.0")

    implementation("com.google.code.gson:gson:2.10.1")

//    implementation("androidx.room:room-ktx:2.6.0")
//    ksp("androidx.room:room-compiler:2.6.0")
}

afterEvaluate {
    publishing {
        publications.create<MavenPublication>("DebugScreenRelease") {
            groupId = "com.github.dmitryskor99"
            artifactId = "debugscreen"
            version = "1.0.1"

            from(components["release"])
        }
    }
}