package com.ink1804.convention.plugin

import com.ink1804.convention.core.libs
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class SqlDelightConventionPlugin : BaseConventionPlugin() {

    override fun Project.configureCommonDependencies() {
        extensions.getByType<KotlinMultiplatformExtension>().apply {
            sourceSets.apply {
                commonMain.dependencies {
                    implementation(libs.findLibrary("sqldelight-runtime").get())
                    implementation(libs.findLibrary("sqldelight-coroutines").get())

                    implementation(libs.findLibrary("russhwolf-settings").get())
                    implementation(libs.findLibrary("russhwolf-settings-serialization").get())
                    implementation(libs.findLibrary("russhwolf-settings-coroutines").get())
                    implementation(libs.findLibrary("kotlinx-serialization-json").get())
                }
            }
        }
    }
}
