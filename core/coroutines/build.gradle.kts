plugins {
    id("com.ink1804.convention.library")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.decompose)
        }
    }
}
