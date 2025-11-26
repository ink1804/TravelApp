plugins {
    id("com.ink1804.convention.feature")
    id("com.ink1804.convention.compose")
    id("com.ink1804.convention.decompose")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(project(":feature:profile:api"))
            implementation(project(":core:ui"))
            implementation(project(":core:resources"))
        }
    }
}
