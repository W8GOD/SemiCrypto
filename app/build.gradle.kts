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
        kotlinCompilerExtensionVersion = "1.0.2"
        kotlinCompilerVersion = "1.5.21"
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

androidExtensions {
    isExperimental = true
}