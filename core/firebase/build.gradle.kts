plugins {
    id("com.ink1804.convention.library")
    id("com.ink1804.convention.di")
}


kotlin {
    sourceSets {
        androidMain.dependencies {
            api(project.dependencies.platform(libs.firebase.bom))
            implementation(libs.firebase.common)
//            implementation(libs.firebase.crashlytics)
//            implementation(libs.firebase.config)
        }
        commonMain.dependencies {
            implementation(project(":core:logger"))
            implementation(libs.gitlive.firebase.auth)
//            implementation(libs.gitlive.firebase.crashlytics)
            implementation(libs.gitlive.firebase.config)
        }
    }
}
