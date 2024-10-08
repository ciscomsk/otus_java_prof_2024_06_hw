subprojects {
    repositories {
        mavenCentral()
    }

    pluginManager.apply("java")

    extensions.configure<JavaPluginExtension> {
        toolchain {
            languageVersion = JavaLanguageVersion.of(21)
        }
    }

    tasks.withType<JavaCompile> {
        options.compilerArgs.addAll(listOf("-Xlint:all"))
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    group = "ru.otus"
    version = "1.0-SNAPSHOT"
}
