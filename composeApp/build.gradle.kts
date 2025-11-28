import org.jetbrains.kotlin.gradle.plugin.mpp.Framework
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    id("com.ink1804.convention.application")
    id("com.ink1804.convention.di")
    id("com.ink1804.convention.sqldelight")
    id("com.google.gms.google-services")
}

kotlin {
    targets.withType<KotlinNativeTarget>().configureEach {
        binaries.withType<Framework>().configureEach {
            export(project(":core:di"))
        }
    }

    sourceSets {
        iosMain.dependencies {
            api(project(":core:di"))
        }
        androidMain.dependencies {
            implementation(libs.androidx.activity.compose)
        }
        commonMain.dependencies {
            implementation(compose.components.resources)

            // Infra modules
            implementation(project(":infra:supabase:impl"))
            implementation(project(":infra:firebase:impl"))
            implementation(project(":core:analytics:impl-composite"))
            implementation(project(":core:storage:impl"))
            implementation(project(":core:database"))

            // Core modules
            implementation(project(":core:app"))
            implementation(project(":core:resources"))
            implementation(project(":core:platform"))
            implementation(project(":core:di"))
            implementation(project(":core:ui"))
            implementation(project(":core:settings:impl"))
            implementation(project(":core:network"))

            // Data modules
            implementation(project(":data:user"))
            implementation(project(":data:auth"))
            implementation(project(":data:config"))

            // Feature modules
            implementation(project(":test:testImpl"))
            implementation(project(":feature:root:ui"))
            implementation(project(":feature:root:impl"))
            implementation(project(":feature:onboarding"))
            implementation(project(":feature:debug:impl"))
            implementation(project(":feature:home:impl"))
            implementation(project(":feature:discovery:impl"))
            implementation(project(":feature:profile:impl"))

        }
    }
}
