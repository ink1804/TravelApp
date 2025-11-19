plugins {
    id("com.ink1804.convention.library")
    id("com.ink1804.convention.di")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.ktor.core)
            implementation(libs.ktor.negotiation)
            implementation(libs.ktor.serialization)
            implementation(libs.ktor.logging)
//            implementation(libs.ktor.websockets)
        }

        androidMain.dependencies {
            implementation(libs.ktor.okhttp)
            implementation(libs.okhttp3.logging.interceptor)
        }

        iosMain.dependencies {
            implementation(libs.ktor.darwin)
        }
    }
}
