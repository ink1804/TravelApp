plugins {
    id("com.ink1804.convention.feature")
    id("com.ink1804.convention.decompose")
    id("com.ink1804.convention.di")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(project(":feature:home:api"))
            implementation(project(":feature:discovery:api"))
            implementation(project(":feature:profile:api"))
            implementation(project(":core:storage:api"))
            implementation(project(":test:testApi"))
        }
    }
}
