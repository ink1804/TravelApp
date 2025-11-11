plugins {
    id("com.ink1804.convention.feature")
    id("com.ink1804.convention.di")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(project(":feature:home:api"))
            implementation(project(":feature:discovery:api"))
            implementation(project(":feature:profile:api"))
        }
    }
}
