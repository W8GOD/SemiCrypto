package semicrypto.plugin.main

import org.gradle.api.Plugin
import org.gradle.api.Project

open class MainPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        project.configureMainPlugins()
        project.configureMainAndroid()
        project.configureMainDependency()
    }
}




