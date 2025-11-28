plugins {
    id("com.ink1804.convention.library")
    id("com.ink1804.convention.di")
    id("com.ink1804.convention.compose")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(project(":core:logger"))
            implementation(project(":infra:supabase:api"))
            implementation(libs.supabase.auth)
            implementation(libs.supabase.postgres)
        }
    }
}
