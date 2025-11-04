package com.ink1804.convention.plugin

import com.ink1804.convention.core.libs
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class DiConventionPlugin : BaseConventionPlugin() {

    override fun Project.configureCommonDependencies() {
        extensions.getByType<KotlinMultiplatformExtension>().apply {
            sourceSets.apply {
                commonMain.dependencies {
                    api(libs.findLibrary("koin-core").get())
                    implementation(libs.findLibrary("koin-compose").get())
                    implementation(libs.findLibrary("koin-compose-viewmodel").get())
                }
            }
        }
    }

    override fun Project.configureAndroidDependencies() {
        extensions.getByType<KotlinMultiplatformExtension>().apply {
            sourceSets.apply {
                androidMain.dependencies {
                    implementation(libs.findLibrary("koin-android").get())
                    implementation(libs.findLibrary("koin-android-compose").get())
                }
            }
        }
    }
}
