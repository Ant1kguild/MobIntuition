import org.jetbrains.compose.desktop.application.dsl.JvmApplicationBuildTypes
import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("jvm")
    id("org.jetbrains.compose")
}

group = "com.mobicon.intuition"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    maven("https://jitpack.io")
    google()
}

dependencies {
    // Note, if you develop a library, you should use compose.desktop.common.
    // compose.desktop.currentOs should be used in launcher-sourceSet
    // (in a separate module for demo project and in testMain).
    // With compose.desktop.common you will also lose @Preview functionality
    implementation(compose.desktop.currentOs)
    implementation(compose.ui)
    implementation(compose.foundation)
    implementation(compose.material)
    implementation(compose.components.resources)

    implementation("io.github.alexzhirkevich:compottie:1.1.2")
    implementation("org.lighthousegames:logging-jvm:1.4.2")
    implementation("ch.qos.logback:logback-core:1.5.3")
    implementation("ch.qos.logback:logback-classic:1.5.2")
    implementation("org.slf4j:slf4j-api:2.0.12")
    implementation("com.github.umjammer:jlayer:1.0.3")
    implementation(kotlin("reflect"))
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            buildTypes {
                release {
                    proguard.optimize = true
                    proguard.isEnabled = true
                }
            }
            packageName = "Intuition"
            packageVersion = "1.0.0"
        }
    }
}
