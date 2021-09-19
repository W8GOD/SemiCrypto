plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
}

object PluginsVersions {
    const val gradle_android_version = "4.2.0"
    const val kotlin_version = "1.5.21"
    const val google_service_version = "4.3.10"
}

dependencies {
    implementation("com.android.tools.build:gradle:${PluginsVersions.gradle_android_version}")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:${PluginsVersions.kotlin_version}")
    implementation("com.google.gms:google-services:${PluginsVersions.google_service_version}")

    implementation(gradleApi())
    implementation(localGroovy())
}