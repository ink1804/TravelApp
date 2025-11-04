package com.ink1804.convention.plugin

import com.ink1804.convention.core.moduleName
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

open class BaseConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.logger.lifecycle("> Applied convention plugin for module: ${target.path}")
        with(target) {
            logger.lifecycle("> Module name: $moduleName")

            configurePlugin()
            configureAndroidPlatform()
            configureIOsPlatform()
            configureCommonDependencies()
            configureAndroidDependencies()
            configureIOsDependencies()
        }
    }

    /**
     * Hook for base plugin configuration.
     * Override in subclasses to provide custom configuration.
     */
    open fun Project.configurePlugin() = Unit

    /**
     * Hook for Android platform configuration.
     * Override in subclasses to provide Android-specific setup.
     */
    open fun Project.configureAndroidPlatform() = Unit

    /**
     * Hook for iOS platform configuration.
     * Override in subclasses to provide iOS-specific setup.
     */
    open fun Project.configureIOsPlatform() = Unit

    /**
     * Hook for configuring common dependencies.
     * Override in subclasses to add dependencies shared across platforms.
     */
    open fun Project.configureCommonDependencies() = Unit

    /**
     * Hook for configuring Android-specific dependencies.
     * Override in subclasses to add Android-only dependencies.
     */
    open fun Project.configureAndroidDependencies() = Unit

    /**
     * Hook for configuring iOS-specific dependencies.
     * Override in subclasses to add iOS-only dependencies.
     */
    open fun Project.configureIOsDependencies() = Unit
}