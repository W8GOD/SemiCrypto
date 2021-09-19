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

    //TODO Remove
    implementation(app.Dependencies.retrofit)
    implementation(app.Dependencies.retrofitConverter)
    implementation(app.Dependencies.okhttp)
    implementation(app.Dependencies.okhttpLogging)
}