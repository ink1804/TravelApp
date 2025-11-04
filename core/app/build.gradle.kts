plugins {
    id("com.ink1804.convention.library")
    id("com.ink1804.convention.di")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.androidx.lifecycle.viewmodel.compose)
                implementation(project(":test:testApi"))
            }
        }
    }
}
