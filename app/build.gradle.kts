import app.DependenciesVersions

plugins {
    id("semicrypto.plugin.main")
    kotlin("android")
    kotlin("android.extensions")
}

android {
    sourceSets["main"].apply {
        java.srcDir("src/main/kotlin")
    }

    buildFeatures { compose = true }
    composeOptions {
        kotlinCompilerExtensionVersion = DependenciesVersions.compose_version
        kotlinCompilerVersion = DependenciesVersions.kotlin_version
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

androidExtensions {
    isExperimental = true
}

dependencies {
    implementation(project(":core"))
    implementation(project(":common"))
}