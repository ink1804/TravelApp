package com.ink1804.convention.plugin

import com.ink1804.convention.core.libs
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension


class DecomposeConventionPlugin : BaseConventionPlugin() {

    override fun Project.configurePlugin() = with(project.pluginManager) {
    }

    override fun Project.configureCommonDependencies() {
        extensions.getByType<KotlinMultiplatformExtension>().apply {
            sourceSets.apply {
                commonMain.dependencies {
                    implementation(libs.findLibrary("decompose").get())
                    implementation(libs.findLibrary("decompose-extensions").get())
                }
            }
        }
    }
}
