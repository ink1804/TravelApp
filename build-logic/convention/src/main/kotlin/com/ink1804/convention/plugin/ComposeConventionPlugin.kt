package com.ink1804.convention.plugin

import com.ink1804.convention.core.libs
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.compose.ComposeExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class ComposeConventionPlugin : BaseConventionPlugin() {

    override fun Project.configurePlugin() = with(project.pluginManager) {
        apply(libs.findPlugin("composeMultiplatform").get().get().pluginId)
        apply(libs.findPlugin("composeCompiler").get().get().pluginId)
    }

    override fun Project.configureCommonDependencies() {
        val composeDependencies = extensions.getByType<ComposeExtension>().dependencies
        extensions.getByType<KotlinMultiplatformExtension>().apply {
            sourceSets.apply {
                commonMain.dependencies {
                    implementation(composeDependencies.runtime)
                    implementation(composeDependencies.foundation)
                    implementation(composeDependencies.material3)
                    implementation(composeDependencies.ui)
                    implementation(composeDependencies.components.resources)
                    implementation(composeDependencies.components.uiToolingPreview)
                    implementation(libs.findLibrary("material-icons-extended").get())
                    implementation(libs.findLibrary("androidx-lifecycle-runtime-compose").get())

                    implementation(project(":core:resources"))
                }
            }
        }
    }
}
