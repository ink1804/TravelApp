plugins {
    id("com.ink1804.convention.library")
    id("com.ink1804.convention.di")
    id("com.ink1804.convention.sqldelight")
    id("app.cash.sqldelight")
}

sqldelight {
//    linkSqlite = true
    databases {
        create("AppDatabase") {
            packageName.set("com.ink1804.core.database")
        }
    }
}

kotlin {
    sourceSets {
        iosMain.dependencies {
            implementation(libs.sqldelight.native)
        }
        androidMain.dependencies {
            implementation(libs.sqldelight.android)
        }
        commonMain.dependencies {
        }
    }
}
