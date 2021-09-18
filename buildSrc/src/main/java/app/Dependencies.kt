package app

object Dependencies {

    object Versions {
        const val core_ktx_version = "1.2.0"
        const val appcompat_version = "1.3.1"
        const val material_design_version = "1.4.0"
        const val compose_version = "1.0.2"
        const val lifecycle_ktx_version = "2.3.1"
        const val compose_activity_version = "1.3.1"
        const val glide_version = "4.11.0"
        const val junit_version = "4.12"
        const val junit_ext_version = "1.1.3"
        const val espresso_version = "3.4.0"
        const val rx_android_version = "2.1.1"
        const val rxkotlin_version = "2.4.0"
        const val gson_version = "2.8.6"
    }

    const val coreKtx = "androidx.core:core-ktx:${Versions.core_ktx_version}"
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat_version}"
    const val materialDesign = "com.google.android.material:material:${Versions.material_design_version}"
    const val composeUI = "androidx.compose.ui:ui:${Versions.compose_version}"
    const val composeMaterial = "androidx.compose.material:material:${Versions.compose_version}"
    const val composePreview = "androidx.compose.ui:ui-tooling-preview:${Versions.compose_version}"
    const val lifecycleKtx = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle_ktx_version}"
    const val composeActivity = "androidx.activity:activity-compose:${Versions.compose_activity_version}"
    const val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glide_version}"
    const val glide = "com.github.bumptech.glide:okhttp3-integration:${Versions.glide_version}"
    const val rxAndroid = "io.reactivex.rxjava2:rxandroid:${Versions.rx_android_version}"
    const val rxKotlin = "io.reactivex.rxjava2:rxkotlin:${Versions.rxkotlin_version}"
    const val gson = "com.google.code.gson:gson:${Versions.gson_version}"

    const val junit = "junit:junit:${Versions.junit_version}"

    const val junitExt = "androidx.test.ext:junit:${Versions.junit_ext_version}"
    const val testComposeUI = "androidx.test.ext:junit:${Versions.junit_ext_version}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso_version}"

    const val composeUITooling = "androidx.compose.ui:ui-tooling:${Versions.compose_version}"
}