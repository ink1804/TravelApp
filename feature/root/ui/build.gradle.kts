plugins {
    id("com.ink1804.convention.feature")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(project(":feature:root:api"))
            implementation(project(":feature:home:ui"))
            implementation(project(":feature:debug:ui"))
            implementation(project(":feature:onboarding"))
        }
    }
}
