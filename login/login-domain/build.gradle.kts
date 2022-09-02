plugins {
    kotlin("jvm")
    id("com.google.devtools.ksp")
}

dependencies {
    implementation(project(":annotations"))
    implementation(project(":login:login-data"))
    ksp(project(":processor"))
    implementation("io.insert-koin:koin-core:3.2.0")
}
