enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
    }
}

rootProject.name = "TravelApp"
include(":composeApp")
include(":core:di")
include(":core:app")
include(":test:testApi")
include(":test:testImpl")
include(":feature:root:api")
include(":feature:root:impl")
include(":feature:discovery:api")
include(":feature:discovery:impl")
include(":feature:profile:api")
include(":feature:profile:impl")
include(":feature:discovery:ui")
include(":feature:profile:ui")
include(":feature:root:ui")
