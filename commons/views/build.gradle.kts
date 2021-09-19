plugins {
    id("com.android.library")
    kotlin("android.extensions")
    kotlin("android")
}

android {
    compileSdkVersion(app.BuildConfigVersion.compileSdkVersion)
    buildToolsVersion(app.BuildConfigVersion.buildToolsVersion)

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    sourceSets["main"].apply {
        java.srcDir("src/main/kotlin")
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

androidExtensions {
    isExperimental = true
}

dependencies {
    implementation(project(":commons:ui"))
}