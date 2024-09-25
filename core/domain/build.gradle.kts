plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
dependencies {
    //Koin
    implementation(project.dependencies.platform("io.insert-koin:koin-bom:3.5.6"))
    implementation(libs.koin.composeVM)
    implementation(libs.koin.compose)
    implementation(libs.insert.koin.koin.core)
    implementation(libs.kotlinx.datetime)
    implementation(project(":core:data"))
}