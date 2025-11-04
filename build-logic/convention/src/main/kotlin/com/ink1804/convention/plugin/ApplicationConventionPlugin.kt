package com.ink1804.convention.plugin

import Config
import com.android.build.api.dsl.ApplicationExtension
import com.ink1804.convention.core.libs
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.get
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.compose.ComposeExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class ApplicationConventionPlugin : BaseConventionPlugin() {

    override fun Project.configurePlugin() = with(project.pluginManager) {
        apply(libs.findPlugin("kotlinMultiplatform").get().get().pluginId)
        apply(libs.findPlugin("androidApplication").get().get().pluginId)
        apply(libs.findPlugin("composeMultiplatform").get().get().pluginId)
        apply(libs.findPlugin("composeCompiler").get().get().pluginId)
    }

    override fun Project.configureAndroidPlatform() {
        val minSdk = libs.findVersion("minSdk").get().requiredVersion.toInt()
        val targetSdk = libs.findVersion("targetSdk").get().requiredVersion.toInt()
        val versionCode = libs.findVersion("versionCode").get().requiredVersion.toInt()
        val versionName = libs.findVersion("versionName").get().requiredVersion
        val applicationId = libs.findVersion("applicationId").get().requiredVersion

        extensions.getByType<ApplicationExtension>().apply {
            namespace = applicationId
            compileSdk = targetSdk
            defaultConfig {
                this.minSdk = minSdk
                this.targetSdk = targetSdk
                this.versionCode = versionCode
                this.versionName = versionName
                this.applicationId = applicationId
            }

            sourceSets["main"].apply {
                manifest.srcFile("src/androidMain/AndroidManifest.xml")
                res.srcDirs("src/androidMain/res")
                resources.srcDirs("src/commonMain/resources")
            }

            packaging {
                resources {
                    excludes += "/META-INF/{AL2.0,LGPL2.1}"
                }
            }
            buildTypes {
                getByName("release") {
                    isMinifyEnabled = false
                }
            }
            compileOptions {
                sourceCompatibility = Config.JAVA_SOURCE
                targetCompatibility = Config.JAVA_TARGET
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
                    this.baseName = "ComposeApp"
                    this.isStatic = true
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
                    implementation(composeDependencies.material3)
                    implementation(composeDependencies.ui)
                }
            }

        }
        dependencies {
            add("debugImplementation", composeDependencies.components.uiToolingPreview)
            add("debugImplementation", composeDependencies.uiTooling)
        }
    }
}
