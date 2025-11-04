plugins {
    id("com.ink1804.convention.library")
    id("com.ink1804.convention.di")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(project(":core:app"))
//                implementation(project(":core:app"))
                implementation(project(":test:testApi"))
                implementation(project(":test:testImpl"))
            }
        }
    }
}
