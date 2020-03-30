import org.jetbrains.kotlin.cli.jvm.main

plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("kotlinx-serialization")
    id("com.android.library")
    id("co.touchlab.kotlinxcodesync")
}

android {
    compileSdkVersion(Versions.compile_sdk)
    defaultConfig {
        minSdkVersion(Versions.min_sdk)
        targetSdkVersion(Versions.target_sdk)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

kotlin {
    version = "1.0"

    android()

    ios()

    sourceSets["commonMain"].dependencies {
        implementation(kotlin("stdlib-common", Versions.kotlin))
        implementation(Dependencies.ktor.commonCore)
        implementation(Dependencies.ktor.commonJson)
        implementation(Dependencies.Coroutines.common)
        implementation(Dependencies.stately)
        implementation(Dependencies.multiplatformSettings)
        implementation(Dependencies.koinCore)
        implementation(Dependencies.ktor.commonSerialization)
        implementation(Dependencies.ktor.commonLogging, Dependencies.coroutinesExcludingCoreNative)
    }

    sourceSets["commonTest"].dependencies {
        implementation(Dependencies.multiplatformSettingsTest)
        implementation(Dependencies.KotlinTest.common)
        implementation(Dependencies.KotlinTest.annotations)
        implementation(Dependencies.Coroutines.jdk)
        implementation(Dependencies.Coroutines.common)
        implementation(Dependencies.Coroutines.test)
    }

    sourceSets["androidMain"].dependencies {
        implementation(kotlin("stdlib", Versions.kotlin))
        implementation(Dependencies.ktor.jvmCore)
        implementation(Dependencies.ktor.jvmJson)
        implementation(Dependencies.Coroutines.jdk)
        implementation(Dependencies.Coroutines.android)
        implementation(Dependencies.ktor.androidSerialization)
        implementation(Dependencies.ktor.androidLogging, Dependencies.coroutinesExcludingCoreNative)
    }

    sourceSets["androidTest"].dependencies {
        implementation(Dependencies.KotlinTest.jvm)
        implementation(Dependencies.KotlinTest.junit)
        implementation(Dependencies.Coroutines.jdk)
        implementation(Dependencies.AndroidXTest.core)
        implementation(Dependencies.AndroidXTest.junit)
        implementation(Dependencies.AndroidXTest.runner)
        implementation(Dependencies.AndroidXTest.rules)
        implementation("org.robolectric:robolectric:4.3.1")
    }

    sourceSets["iosMain"].dependencies {
        implementation(Dependencies.ktor.ios, Dependencies.coroutinesExcludingCoreNative)
        implementation(Dependencies.ktor.iosCore, Dependencies.coroutinesExcludingCoreNative)
        implementation(Dependencies.ktor.iosJson, Dependencies.coroutinesExcludingCoreNative)
        implementation(Dependencies.ktor.iosLogging, Dependencies.coroutinesExcludingCoreNative)
        implementation(Dependencies.Coroutines.native)
        implementation(Dependencies.ktor.iosSerialization)
    }

    xcodeSync {
        projectPath = "../../iOS/Example.xcworkspace"
        target = "Example"
    }

    cocoapods {
        homepage = "https://github.com/vitorhugods/KotlinNativeMobileExample"
        summary = "This is not a drill."
    }
}

val iOSTest: Task by tasks.creating {
    val device = project.findProperty("iosDevice")?.toString() ?: "iPhone 8"
    dependsOn("linkDebugTestIos")
    description = "Runs 'ios' tests using a simulator"
    group = JavaBasePlugin.VERIFICATION_GROUP

    doLast {
        val binary = kotlin.targets.getByName<org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget>("ios").binaries.getTest("DEBUG").outputFile
        exec {
            commandLine("xcrun", "simctl", "spawn", "--standalone", device, binary.absolutePath)
        }
    }
}
