import java.io.FileInputStream
import java.util.Properties
import org.gradle.api.JavaVersion
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

object Config {
    const val COMPILE_SDK_VERSION = 36
    const val TARGET_SDK_VERSION = 36
    const val MIN_SDK_VERSION = 24

    val JAVA_SOURCE = JavaVersion.VERSION_21
    val JAVA_TARGET = JavaVersion.VERSION_21
    val JVM_TARGET = JvmTarget.JVM_21

    fun getVersionName(): String {
        return getVersionProperties()["version.name"].toString()
    }

    fun getVersionCode(): Int {
        return getVersionProperties()["version.code"].toString().toInt()
    }

    private fun getVersionProperties(): Properties {
        val input = FileInputStream("version.properties")
        return Properties().apply {
            load(input)
        }
    }
}