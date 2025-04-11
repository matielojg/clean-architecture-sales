// build.gradle.kts (raiz)
plugins {
    // Nenhum plugin aqui
}

allprojects {
    group = "com.github.matielojg"
    version = "0.0.1-SNAPSHOT"
    repositories {
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "java")

    tasks.withType<Jar>().configureEach {
        enabled = true
    }

    plugins.withId("org.jetbrains.kotlin.jvm") {
        // Configuração comum Kotlin
    }

    plugins.withId("org.springframework.boot") {
        // Configuração comum Spring Boot
    }
}
