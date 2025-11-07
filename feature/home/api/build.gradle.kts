plugins {
    id("com.ink1804.convention.feature")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(project(":feature:discovery:api"))
            implementation(project(":feature:profile:api"))
        }
    }
}
