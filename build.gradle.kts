plugins {
    id("org.sonarqube") version "4.4.1.3373"
}

extra["junitVersion"] = "5.10.0"
extra["mockitoVersion"] = "5.10.0"
extra["lombokVersion"] = "1.18.36"
extra["springBootVersion"] = "3.4.4"

allprojects {
    group = "com.github.matielojg"
    version = "0.0.1-SNAPSHOT"

    repositories {
        mavenLocal()
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "java")

    // Permite builds sem erro em subprojetos sem main class
    tasks.withType<Jar>().configureEach {
        enabled = true
    }

    // Plugins comuns por identificação
    plugins.withId("org.jetbrains.kotlin.jvm") {
        // Shared Kotlin config (se houver)
    }

    plugins.withId("org.springframework.boot") {
        // Shared Spring Boot config (se necessário)
    }
}

sonarqube {
    properties {
        property("sonar.projectKey", "revenda-pedidos-api")
        property("sonar.host.url", "http://localhost:9000")
        val token = System.getenv("SONAR_TOKEN") ?: System.getProperty("SONAR_TOKEN")
        if (token != null) {
            property("sonar.token", token)
        } else {
            error("Missing SONAR_TOKEN (env or -DSONAR_TOKEN)")
        }
    }
}
