plugins {
    id("com.ink1804.convention.library")
    id("com.ink1804.convention.di")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(project(":core:logger"))
            api(project(":infra:supabase:api"))
            api(project(":infra:sqldelight:api"))
        }
    }
}
