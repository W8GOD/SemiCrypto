
plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
}

dependencies {
    implementation("com.android.tools.build:gradle:4.2.0")
    implementation("com.google.code.gson:gson:2.8.6")
    implementation("com.google.gms:google-services:4.3.10")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.21")

    implementation(gradleApi())
    implementation(localGroovy())
}