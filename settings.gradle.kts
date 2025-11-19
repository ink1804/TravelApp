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

include(":core:analytics:api")
include(":core:analytics:impl-composite")
include(":core:analytics:impl-debug")
include(":feature:debug:api")
include(":feature:debug:impl")
include(":feature:debug:ui")
include(":core:platform")
include(":core:config:api")
include(":core:config:impl")
include(":core:storage:api")
include(":core:storage:impl")
include(":core:database")
include(":core:network")
