package com.ink1804.convention.plugin

import com.codingfeline.buildkonfig.compiler.FieldSpec
import com.codingfeline.buildkonfig.gradle.BuildKonfigExtension
import com.ink1804.convention.core.libs
import com.ink1804.convention.core.moduleName
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure


class KonfigConventionPlugin : BaseConventionPlugin() {

    override fun Project.configurePlugin() = with(project.pluginManager) {
        apply(libs.findPlugin("buildKonfig").get().get().pluginId)
    }

    override fun Project.configureCommonDependencies() {
        extensions.configure<BuildKonfigExtension> {
            packageName = moduleName

            defaultConfigs {
                buildConfigField(FieldSpec.Type.BOOLEAN, "DEBUG", "false")
            }

            defaultConfigs("dev") {
                buildConfigField(FieldSpec.Type.BOOLEAN, "DEBUG", "true")
            }

            defaultConfigs("prod") {
                buildConfigField(FieldSpec.Type.BOOLEAN, "DEBUG", "false")
            }
        }
    }
}
