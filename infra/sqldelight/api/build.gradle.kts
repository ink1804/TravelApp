plugins {
    id("com.ink1804.convention.library")
    id("app.cash.sqldelight")
}

sqldelight {
//    linkSqlite = true
    databases {
        create("AppDatabase") {
            packageName.set("com.ink1804.infra.database")
        }
    }
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.sqldelight.runtime)
        }
    }
}
