plugins {
    id("com.ink1804.convention.feature")
    id("com.ink1804.convention.di")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(project(":feature:root:api"))
            implementation(project(":feature:home:api"))
            implementation(project(":feature:debug:api"))
            implementation(project(":core:platform"))
        }
    }
}
