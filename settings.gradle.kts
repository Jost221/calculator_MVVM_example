pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
    plugins {
        id("com.android.application") version "8.3.0" apply false
        id("com.android.library") version "8.3.0" apply false
        id("org.jetbrains.kotlin.android") version "1.9.23" apply false // Соответствие версии Kotlin
        id("com.google.devtools.ksp") version "1.9.23-1.0.19" apply false // Применение KSP
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "calculator"
include(":app")
 