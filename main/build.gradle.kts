plugins {
    kotlin("jvm")
    application
    id("com.google.devtools.ksp")
}

// Allows running from command line using  ./gradlew :main-project:run
application {
    mainClass.set("de.ams.MainKt")
}

// Makes generated code visible to IDE
kotlin.sourceSets.main {
    kotlin.srcDirs(
        file("$buildDir/generated/ksp/main/kotlin"),
    )
}

dependencies {
    ksp(project(":processor"))
    implementation(project(":annotations"))
    implementation(project(":login:login-presentation"))
    implementation(project(":login:login-domain"))
    implementation(project(":login:login-data"))
    implementation(project(":login:login-common"))
    implementation(project(":dashboard:dashboard-presentation"))
    implementation(project(":dashboard:dashboard-domain"))
    implementation(project(":dashboard:dashboard-data"))
    implementation(project(":dashboard:dashboard-common"))
    implementation("com.jakewharton.timber:timber:4.7.1")
    implementation("io.insert-koin:koin-core:3.2.0")
}
