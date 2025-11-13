import org.jetbrains.kotlin.gradle.plugin.mpp.Framework
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    id("com.ink1804.convention.application")
    id("com.ink1804.convention.di")
}

kotlin {
    sourceSets {
        targets.withType<KotlinNativeTarget>().configureEach {
            binaries.withType<Framework>().configureEach {
                export(project(":core:di"))
            }
        }
        iosMain.dependencies {
            api(project(":core:di"))
        }
        androidMain.dependencies {
            implementation(libs.androidx.activity.compose)
        }
        commonMain.dependencies {
            implementation(compose.components.resources)

            // Core modules
            implementation(project(":core:app"))
            implementation(project(":core:platform"))
            implementation(project(":core:di"))
            implementation(project(":core:config:impl"))
            implementation(project(":core:analytics:impl-composite"))

            // Feature modules
            implementation(project(":feature:root:ui"))
            implementation(project(":feature:root:impl"))
            implementation(project(":feature:debug:impl"))
            implementation(project(":feature:home:impl"))
            implementation(project(":feature:discovery:impl"))
            implementation(project(":feature:profile:impl"))

        }
    }
}
