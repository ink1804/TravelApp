import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

group = "com.ink1804.convention"

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_21.toString()
    }
}

dependencies {
    implementation(libs.gradle)
    implementation(libs.kotlin.multiplatform.gradle.plugin)
    implementation(libs.kotlin.gradle.plugin)
    implementation(libs.google.services)
    implementation(libs.compose.gradle.plugin)
}

gradlePlugin {
    plugins {
        register("com.ink1804.convention.library"){
            id = "com.ink1804.convention.library"
            implementationClass = "com.ink1804.convention.plugin.LibraryConventionPlugin"
        }
        register("com.ink1804.convention.di"){
            id = "com.ink1804.convention.di"
            implementationClass = "com.ink1804.convention.plugin.DiConventionPlugin"
        }
        register("com.ink1804.convention.feature"){
            id = "com.ink1804.convention.feature"
            implementationClass = "com.ink1804.convention.plugin.FeatureConventionPlugin"
        }
        register("com.ink1804.convention.application"){
            id = "com.ink1804.convention.application"
            implementationClass = "com.ink1804.convention.plugin.ApplicationConventionPlugin"
        }
    }
}