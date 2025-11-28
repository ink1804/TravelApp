plugins {
    id("com.ink1804.convention.library")
    id("com.ink1804.convention.sqldelight")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(project(":core:storage:api"))
            implementation(project(":infra:sqldelight:api"))
        }
    }
}