package semicrypto.plugin.main

import app.Dependencies
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureMainDependency() = dependencies {
    add("implementation", fileTree("libs") { include("*.jar") })

    add("implementation", Dependencies.coreKtx)
    add("implementation", Dependencies.appcompat)
    add("implementation", Dependencies.materialDesign)
    add("implementation", Dependencies.composeUI)
    add("implementation", Dependencies.composeMaterial)
    add("implementation", Dependencies.composePreview)
    add("implementation", Dependencies.lifecycleKtx)
    add("implementation", Dependencies.composeActivity)
    add("implementation", Dependencies.rxAndroid)
    add("implementation", Dependencies.rxKotlin)
    add("implementation", Dependencies.gson)
    add("implementation", Dependencies.pagingCompose)
    add("implementation", Dependencies.swiperefresh)
    add("implementation", Dependencies.coil)
    add("implementation", Dependencies.coilCompose)
    add("implementation", Dependencies.coilSvg)

    add("testImplementation", Dependencies.junit)

    add("androidTestImplementation", Dependencies.junitExt)
    add("androidTestImplementation", Dependencies.testComposeUI)
    add("androidTestImplementation", Dependencies.espresso)

    add("debugImplementation", Dependencies.composeUITooling)
}