plugins {
    id("com.ink1804.convention.feature")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(project(":feature:debug:api"))
            implementation(project(":core:ui"))
        }
    }
}
