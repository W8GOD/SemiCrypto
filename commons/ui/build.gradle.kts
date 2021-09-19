import app.Dependencies

plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
}

dependencies {
    implementation(Dependencies.coreKtx)
    implementation(Dependencies.lifecycleExtensions)
    implementation(Dependencies.lifecycleViewModel)
}


