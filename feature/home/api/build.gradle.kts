plugins {
    id("com.ink1804.convention.library")
    id("com.ink1804.convention.decompose")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(project(":feature:discovery:api"))
            implementation(project(":feature:profile:api"))
        }
    }
}
