plugins {
    id("com.ink1804.convention.feature")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(project(":feature:home:api"))
            implementation(project(":core::ui"))
            implementation(project(":feature:profile:ui"))
            implementation(project(":feature:discovery:ui"))
        }
    }
}
