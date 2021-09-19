package semicrypto.plugin.main

import app.BuildConfigVersion
import com.android.build.gradle.BaseExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

internal fun Project.configureMainAndroid() = this.extensions.getByType<BaseExtension>().run {

    compileSdkVersion(BuildConfigVersion.compileSdkVersion)
    buildToolsVersion(BuildConfigVersion.buildToolsVersion)

    defaultConfig {
        minSdkVersion(BuildConfigVersion.minSdkVersion)
        targetSdkVersion(BuildConfigVersion.targetSdkVersion)

        applicationId = BuildConfigVersion.applicationId

        versionCode = BuildConfigVersion.versionCode
        versionName = BuildConfigVersion.versionName

        multiDexEnabled = true
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true

        buildTypes {
            getByName("debug") {
                splits.abi.isEnable = false
                splits.density.isEnable = false
                aaptOptions.cruncherEnabled = false
                isTestCoverageEnabled = true
            }
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }

        dexOptions {
            preDexLibraries = true
        }

        testOptions {
            unitTests.isReturnDefaultValues = true
        }

        packagingOptions {
            exclude("libs/arm64-v8a/libHDACEngine.so")
            pickFirst("lib/mips/librsjni.so")
            pickFirst("lib/mips/libRSSupport.so")
            exclude("META-INF/LICENSE.txt")
            exclude("META-INF/LICENSE")
            exclude("META-INF/ASL2.0")
            exclude("META-INF/NOTICE")
            exclude("META-INF/NOTICE.txt")
            exclude("META-INF/services/javax.annotation.processing.Processor")
            exclude("META-INF/rxjava.properties")
            exclude("META-INF/*.kotlin_module")
        }

        useLibrary("android.test.runner")
        useLibrary("android.test.base")
        useLibrary("android.test.mock")
    }
}
