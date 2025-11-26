plugins {
    id("com.ink1804.convention.library")
    id("com.ink1804.convention.decompose")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(project(":core:settings:api"))
            implementation(project(":feature:home:api"))
            implementation(project(":feature:debug:api"))
            implementation(project(":feature:onboarding"))
        }
    }
}
