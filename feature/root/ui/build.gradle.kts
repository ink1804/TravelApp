plugins {
    id("com.ink1804.convention.feature")
    id("com.ink1804.convention.compose")
    id("com.ink1804.convention.decompose")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(project(":core:settings:api"))
            implementation(project(":core:ui"))

            implementation(project(":feature:root:api"))
            implementation(project(":feature:home:ui"))
            implementation(project(":feature:debug:ui"))
            implementation(project(":feature:onboarding"))
        }
    }
}
