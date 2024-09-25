plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
    alias(libs.plugins.sqldelight)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
        implementation(libs.ksoup)
        implementation(libs.ksoup.network)
        //SQL Delight
        implementation(libs.app.cash.sqldelight.sqlite)
        implementation(libs.app.cash.sqldelight.coroutines)

        implementation(libs.kotlinx.datetime)

    }

sqldelight {
    databases {
        create("MeetingsDB") {
            packageName.set("com.example.organizer.db")
        }
    }
}