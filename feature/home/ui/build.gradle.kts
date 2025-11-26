plugins {
    id("com.ink1804.convention.feature")
    id("com.ink1804.convention.compose")
    id("com.ink1804.convention.decompose")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(project(":feature:home:api"))
            implementation(project(":core:ui"))
            implementation(project(":feature:profile:ui"))
            implementation(project(":feature:discovery:ui"))
        }
    }
}
