plugins {
    id("com.ink1804.convention.library")
    id("com.ink1804.convention.di")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(project(":core:app"))
            api(project(":core:platform"))
            implementation(project(":test:testApi"))
            implementation(project(":test:testImpl"))

            implementation(project(":core:analytics:impl-composite"))

            implementation(project(":feature:root:impl"))
            implementation(project(":feature:debug:impl"))
            implementation(project(":feature:home:impl"))
            implementation(project(":feature:discovery:impl"))
            implementation(project(":feature:profile:impl"))
        }
    }
}
