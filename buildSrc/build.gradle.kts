plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
}

object PluginsVersions {
    const val GRADLE_ANDROID = "4.2.0"
    const val KOTLIN = "1.5.21"
    const val GOOGLE_SERVICE = "4.3.10"
}

dependencies {
    implementation("com.android.tools.build:gradle:${PluginsVersions.GRADLE_ANDROID}")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:${PluginsVersions.KOTLIN}")
    implementation("com.google.gms:google-services:${PluginsVersions.GOOGLE_SERVICE}")

    implementation(gradleApi())
    implementation(localGroovy())
}