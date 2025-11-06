plugins {
    id("com.ink1804.convention.feature")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(project(":feature:root:api"))
            implementation(project(":feature:profile:ui"))
            implementation(project(":feature:discovery:ui"))
        }
    }
}
