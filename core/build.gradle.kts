import app.Dependencies

plugins {
    id("com.android.library")
    kotlin("android.extensions")
    kotlin("android")
    kotlin("kapt")
}

repositories {
    google()
    mavenCentral()
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

    buildTypes.forEach {
        try {
            it.buildConfigField("String", "API_BASE_URL", "\"https://reqres.in/api/\"")
            it.buildConfigField(
                "String",
                "API_KEY",
                "\"coinrankingc76def0f8c803820a6e32aa198f2e8d6e015e6f35a6ddcef\""
            )
        } catch (ignored: Exception) {
            throw GradleException(ignored.message ?: "")
        }
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

androidExtensions {
    isExperimental = true
}

dependencies {
    implementation(Dependencies.room)
    implementation(Dependencies.roomKtx)
    implementation(Dependencies.lifecycleExtensions)
    implementation(Dependencies.coreKtx)
    implementation(Dependencies.retrofit)
    implementation(Dependencies.retrofitConverter)
    implementation(Dependencies.retrofitAdapter)
    implementation(Dependencies.okhttp)
    implementation(Dependencies.okhttpLogging)
    implementation(Dependencies.rxJavaPaging)
    implementation(Dependencies.pagingRuntime)

    kapt(Dependencies.roomCompiler)
}