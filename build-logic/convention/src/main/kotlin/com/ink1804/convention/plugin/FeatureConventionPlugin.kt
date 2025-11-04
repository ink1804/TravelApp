package com.ink1804.convention.plugin

import Config
import com.android.build.api.dsl.androidLibrary
import com.ink1804.convention.core.libs
import com.ink1804.convention.core.moduleName
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.compose.ComposeExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

@Suppress("UnstableApiUsage")
class FeatureConventionPlugin : BaseConventionPlugin() {
    //todo configure when first feature appear

    override fun Project.configurePlugin() = with(project.pluginManager) {
        apply(libs.findPlugin("kotlinMultiplatform").get().get().pluginId)
        apply(libs.findPlugin("androidKotlinMultiplatformLibrary").get().get().pluginId)
        apply(libs.findPlugin("composeMultiplatform").get().get().pluginId)
        apply(libs.findPlugin("composeCompiler").get().get().pluginId)
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
            androidTarget().apply {
                compilerOptions {
                    jvmTarget.set(Config.JVM_TARGET)
                }
            }

            listOf(
                iosX64(),
                iosArm64(),
                iosSimulatorArm64()
            ).forEach { iosTarget ->
                iosTarget.binaries.framework {
                    baseName = moduleName.replace(".", "-")
                    isStatic = true
                }
            }
        }
    }

    override fun Project.configureCommonDependencies() {
        val composeDependencies = extensions.getByType<ComposeExtension>().dependencies
        extensions.getByType<KotlinMultiplatformExtension>().apply {
            sourceSets.apply {
                commonMain {
                    compilerOptions {
                        freeCompilerArgs.add("-Xcontext-receivers")
                    }
                }
                commonMain.dependencies {
                    implementation(composeDependencies.runtime)
                    implementation(composeDependencies.foundation)
                    implementation(composeDependencies.material)
                    implementation(composeDependencies.ui)
                    implementation(composeDependencies.components.uiToolingPreview)

                    implementation(libs.findLibrary("kotlinx-coroutines-core").get())
                    implementation(libs.findLibrary("androidx-lifecycle-viewmodel-compose").get())
                    implementation(libs.findLibrary("androidx-lifecycle-runtime-compose").get())
                }
            }
        }
    }
}
