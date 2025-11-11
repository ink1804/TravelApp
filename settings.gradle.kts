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
include(":core:ui")
include(":core:logger")
include(":core:coroutines")

include(":test:testApi")
include(":test:testImpl")

include(":feature:root:api")
include(":feature:root:impl")
include(":feature:root:ui")
include(":feature:discovery:api")
include(":feature:discovery:impl")
include(":feature:discovery:ui")
include(":feature:profile:api")
include(":feature:profile:impl")
include(":feature:profile:ui")
include(":feature:home:api")
include(":feature:home:ui")
include(":feature:home:impl")

