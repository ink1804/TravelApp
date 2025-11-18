plugins {
    id("com.ink1804.convention.library")
    id("com.ink1804.convention.di")
    id("com.ink1804.convention.sqldelight")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(project(":core:storage:api"))
            implementation(project(":core:database"))
        }
    }
}
