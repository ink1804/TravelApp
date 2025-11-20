package com.ink1804.convention.plugin

import com.android.build.api.dsl.androidLibrary
import com.ink1804.convention.core.libs
import com.ink1804.convention.core.moduleName
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension


@Suppress("UnstableApiUsage")
class LibraryConventionPlugin : BaseConventionPlugin() {

    override fun Project.configurePlugin() = with(project.pluginManager) {
        apply(libs.findPlugin("kotlinMultiplatform").get().get().pluginId)
        apply(libs.findPlugin("androidKotlinMultiplatformLibrary").get().get().pluginId)
        apply(libs.findPlugin("kotlinSerialization").get().get().pluginId)
        apply("com.ink1804.convention.konfig")
    }

    override fun Project.configureAndroidPlatform() {
        val minSdk = libs.findVersion("minSdk").get().requiredVersion.toInt()
        val compileSdk = libs.findVersion("compileSdk").get().requiredVersion.toInt()

        extensions.configure<KotlinMultiplatformExtension> {
            androidLibrary {
                this.namespace = moduleName
                this.compileSdk = compileSdk
                this.minSdk = minSdk
            }
        }
    }

    override fun Project.configureIOsPlatform() {
        extensions.getByType<KotlinMultiplatformExtension>().apply {
            listOf(
                iosX64(),
                iosArm64(),
                iosSimulatorArm64(),
            ).forEach { iosTarget ->
                iosTarget.binaries.framework {
                    baseName = moduleName.replace(".", "-")
                    isStatic = true
                }
            }
        }
    }

    override fun Project.configureCommonDependencies() {
        extensions.getByType<KotlinMultiplatformExtension>().apply {
            sourceSets.apply {
                commonMain.dependencies {
                    implementation(libs.findLibrary("kotlinx-coroutines-core").get())
                    implementation(libs.findLibrary("kotlinx-serialization-json").get())
                }
            }
        }
    }
}
