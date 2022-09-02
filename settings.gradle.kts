pluginManagement {
    // Versions are declared in 'gradle.properties' file
    val kotlinVersion: String by settings
    val kspVersion: String by settings
    plugins {
        id("com.google.devtools.ksp") version kspVersion
        kotlin("jvm") version kotlinVersion
    }
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "ksp-kkon"

include(":dashboard:dashboard-common")
include(":dashboard:dashboard-data")
include(":dashboard:dashboard-domain")
include(":dashboard:dashboard-presentation")
include(":dashboard")
include(":login:login-common")
include(":login:login-data")
include(":login:login-domain")
include(":login:login-presentation")
include(":login")
include(":annotations")
include(":processor")
include(":main")