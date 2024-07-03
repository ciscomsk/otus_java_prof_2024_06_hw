plugins {
    alias(libs.plugins.shadow)
}

dependencies {
    implementation(libs.guava)
}

tasks {
    shadowJar {
        manifest {
            attributes(mapOf("Main-Class" to "ru.otus.HelloOtus"))
        }
    }
}