import org.jetbrains.compose.resources.ResourcesExtension

plugins {
    id("com.ink1804.convention.library")
    id("com.ink1804.convention.di")
    id(libs.plugins.composeMultiplatform.get().pluginId)
    id(libs.plugins.composeCompiler.get().pluginId)
}

kotlin {
    androidLibrary {
        experimentalProperties["android.experimental.kmp.enableAndroidResources"] = true
    }

    sourceSets {
        commonMain.dependencies {
            implementation(compose.components.resources)
        }
    }
}
compose.resources {
    publicResClass = true
//    packageOfResClass = "com.ink1804.core.resources"
    generateResClass = ResourcesExtension.ResourceClassGeneration.Always
}