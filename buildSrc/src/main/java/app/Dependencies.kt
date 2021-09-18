package app

object Dependencies {
    const val coreKtx = "androidx.core:core-ktx:${DependenciesVersions.core_ktx_version}"
    const val appcompat = "androidx.appcompat:appcompat:${DependenciesVersions.appcompat_version}"
    const val materialDesign = "com.google.android.material:material:${DependenciesVersions.material_design_version}"
    const val composeUI = "androidx.compose.ui:ui:${DependenciesVersions.compose_version}"
    const val composeMaterial = "androidx.compose.material:material:${DependenciesVersions.compose_version}"
    const val composePreview = "androidx.compose.ui:ui-tooling-preview:${DependenciesVersions.compose_version}"
    const val lifecycleKtx = "androidx.lifecycle:lifecycle-runtime-ktx:${DependenciesVersions.lifecycle_ktx_version}"
    const val composeActivity = "androidx.activity:activity-compose:${DependenciesVersions.compose_activity_version}"
    const val glideCompiler = "com.github.bumptech.glide:compiler:${DependenciesVersions.glide_version}"
    const val glide = "com.github.bumptech.glide:okhttp3-integration:${DependenciesVersions.glide_version}"
    const val rxAndroid = "io.reactivex.rxjava2:rxandroid:${DependenciesVersions.rx_android_version}"
    const val rxKotlin = "io.reactivex.rxjava2:rxkotlin:${DependenciesVersions.rxkotlin_version}"
    const val gson = "com.google.code.gson:gson:${DependenciesVersions.gson_version}"

    const val junit = "junit:junit:${DependenciesVersions.junit_version}"

    const val junitExt = "androidx.test.ext:junit:${DependenciesVersions.junit_ext_version}"
    const val testComposeUI = "androidx.test.ext:junit:${DependenciesVersions.junit_ext_version}"
    const val espresso = "androidx.test.espresso:espresso-core:${DependenciesVersions.espresso_version}"

    const val composeUITooling = "androidx.compose.ui:ui-tooling:${DependenciesVersions.compose_version}"
}