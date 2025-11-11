plugins {
    id("com.ink1804.convention.library")
    id("com.ink1804.convention.di")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(project(":core:app"))
            implementation(project(":test:testApi"))
            implementation(project(":test:testImpl"))

            implementation(project(":core:analytics:impl-composite"))

            implementation(project(":feature:root:api"))
            implementation(project(":feature:root:impl"))
            implementation(project(":feature:home:api"))
            implementation(project(":feature:home:impl"))
            implementation(project(":feature:discovery:api"))
            implementation(project(":feature:discovery:impl"))
            implementation(project(":feature:profile:api"))
            implementation(project(":feature:profile:impl"))
        }
    }
}
