package app

object Dependencies {
    const val coreKtx = "androidx.core:core-ktx:${DependenciesVersions.core_ktx_version}"
    const val appcompat = "androidx.appcompat:appcompat:${DependenciesVersions.appcompat_version}"
    const val materialDesign = "com.google.android.material:material:${DependenciesVersions.material_design_version}"

    // ViewModel and LiveData
    const val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:${DependenciesVersions.lifecycle_version}"
    const val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${DependenciesVersions.lifecycle_version}"
    const val lifecycleKtx = "androidx.lifecycle:lifecycle-runtime-ktx:${DependenciesVersions.lifecycle_ktx_version}"

    // Gson
    const val gson = "com.google.code.gson:gson:${DependenciesVersions.gson_version}"

    // Glide
    const val glideCompiler = "com.github.bumptech.glide:compiler:${DependenciesVersions.glide_version}"
    const val glide = "com.github.bumptech.glide:okhttp3-integration:${DependenciesVersions.glide_version}"

    // Retrofit
    const val retrofit = "com.squareup.retrofit2:retrofit:${DependenciesVersions.retrofit_version}"
    const val retrofitConverter = "com.squareup.retrofit2:converter-gson:${DependenciesVersions.retrofit_version}"

    // RX
    const val rxAndroid = "io.reactivex.rxjava2:rxandroid:${DependenciesVersions.rx_android_version}"
    const val rxKotlin = "io.reactivex.rxjava2:rxkotlin:${DependenciesVersions.rxkotlin_version}"

    // Compose
    const val composeUI = "androidx.compose.ui:ui:${DependenciesVersions.compose_version}"
    const val composeMaterial = "androidx.compose.material:material:${DependenciesVersions.compose_version}"
    const val composePreview = "androidx.compose.ui:ui-tooling-preview:${DependenciesVersions.compose_version}"
    const val composeActivity = "androidx.activity:activity-compose:${DependenciesVersions.compose_activity_version}"
    const val composeUITooling = "androidx.compose.ui:ui-tooling:${DependenciesVersions.compose_version}"

    // Paging Compose
    const val pagingCompose = "androidx.paging:paging-compose:${DependenciesVersions.paging_compose_version}"

    const val junit = "junit:junit:${DependenciesVersions.junit_version}"

    const val junitExt = "androidx.test.ext:junit:${DependenciesVersions.junit_ext_version}"
    const val testComposeUI = "androidx.test.ext:junit:${DependenciesVersions.junit_ext_version}"
    const val espresso = "androidx.test.espresso:espresso-core:${DependenciesVersions.espresso_version}"

}