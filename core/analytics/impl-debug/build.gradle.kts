plugins {
    id("com.ink1804.convention.library")
    id("com.ink1804.convention.di")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(project(":core:analytics:api"))
            implementation(project(":core:logger"))
        }
    }
}
