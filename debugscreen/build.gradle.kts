plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("maven-publish")
}

android {
    namespace = "ru.wb.debugscreen"
    compileSdk = 33

    defaultConfig {
        minSdk = 21

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    ksp {
        arg("room.schemaLocation", "$projectDir/schemas")
        arg("room.incremental", "true")
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
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
//
//java {
//    toolchain {
//        languageVersion = JavaLanguageVersion.of(11)
//    }
//}


java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

dependencies {

    implementation("androidx.compose.material3:material3:1.1.2")

    implementation("com.squareup.retrofit2:retrofit:2.9.0")

    implementation("com.google.code.gson:gson:2.10.1")

    implementation("androidx.room:room-ktx:2.4.1")
//    implementation("androidx.room:room-runtime:2.4.1")
    ksp("androidx.room:room-compiler:2.4.1")
}

afterEvaluate {
    publishing {
        publications.create<MavenPublication>("DebugScreenRelease") {
            groupId = "com.github.dmitryskor99"
            artifactId = "debugscreen"
            version = "1.0.0"

            from(components["release"])
        }
    }
}