plugins {
    id("com.ink1804.convention.feature")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(project(":feature:home:api"))
            implementation(project(":feature:debug:api"))
        }
    }
}
