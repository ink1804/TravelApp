plugins {
    id("com.ink1804.convention.application")
    id("com.ink1804.convention.di")
}

kotlin {
    sourceSets {
        iosMain.dependencies {}
        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
        }
        commonMain.dependencies {
            implementation(compose.runtime)

            implementation(compose.components.resources)
            implementation(libs.koin.core)
            implementation(libs.koin.compose.viewmodel)
            implementation(project(":core:app"))
            implementation(project(":core:di"))
        }
    }
}
